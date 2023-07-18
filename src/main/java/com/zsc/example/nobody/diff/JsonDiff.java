package com.zsc.example.nobody.diff;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.common.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: nobody_demo
 * @description: json 对比
 * @author: zhangsc
 * @create: 2022-10-12 20:00
 * @Copyright: HOSE合思｜易快报
 **/
public class JsonDiff {
    @Test
   public void jsonDiff(){
       // 2个顺序不一样的json字符串 通过 JSONObject.parse(dui)打印出来后 顺序就是一摸一样的了(前提：key都一样)  然后再通过一个字符串查询就行了
       String dui = "{\"adGroupVO\":{\"campaignId\":\"CAMPAIGN201912101000004559\",\"adGroupChannel\":{\"channelType\":\"SMS\",\"resourceCode\":\"TEMPLATE_CODE\"},\"deliveryScene\":\"default\",\"adGroupTimeConfigVO\":{\"startDate\":\"2020-01-02\",\"endDate\":\"2020-01-04\",\"adGroupTimeConfigDetailVoList\":[{\"endTime\":\"20:11\",\"startTime\":\"09:11\"}]},\"adGroupScheduleConfig\":{\"periodic\":false,\"excludeDate\":[\"\"],\"periodInfo\":\"{}\"},\"eventConfig\":{},\"crowdTargetingList\":[{\"bizDate\":\"20191226\",\"principalType\":\"USER_ID\",\"crowdDesc\":\"\",\"crowdId\":\"191226200530888\",\"crowdName\":\"ZGX_已映射_会员域1W用户(清洗后)\",\"crowdSource\":\"FILE\",\"crowdStatus\":1,\"crowdTotalCount\":10000,\"crowdType\":\"FILE\",\"dataUpdateDate\":\"20191226\",\"dataUpdateType\":\"ONCE\",\"expireDate\":\"20201220\",\"fileContentList\":[],\"fileName\":\"会员域人群(清洗后)10000个用户.txt\",\"gmtCreate\":\"2019-12-26 20:05:51\",\"gmtModified\":\"2019-12-26 20:05:51\",\"gmtModifier\":\"zhangguoxiang@sitTest.com\",\"gmtModifierId\":\"zhangguoxiang@sitTest.com\",\"groupConditions\":[],\"operatorId\":\"zhangguoxiang@sitTest.com\",\"operatorName\":\"zhangguoxiang@sitTest.com\",\"ossPath\":\"\",\"parentTaskName\":\"\",\"projectName\":\"\",\"sceneId\":\"DEFAULT\",\"storeType\":\"\",\"tableName\":\"\",\"tenantId\":\"DANAW3ID\",\"userIdColumn\":\"\",\"userIdType\":\"USER_ID\",\"userProperty\":\"\",\"useSceneType\":\"TARGETING\"}],\"fatigueLimitVOList\":[],\"name\":\"任务名称_20200102194954313\",\"priority\":1,\"ruleTargetingVOList\":[],\"terminalTargetingList\":[],\"contentVOList\":[{\"creativeVOList\":[{\"name\":\"测试\",\"creativeType\":\"TEXT\",\"creativeDetailVoList\":[{\"language\":\"en_US\",\"defaultOrNot\":true,\"variables\":\"{\\\"text\\\":\\\"内容\\\",\\\"title\\\":\\\"标题\\\"}\"}],\"extendInfo\":\"{\\\"isEmpty\\\":false}\",\"shunt\":100}]}],\"bizType\":\"content_task\",\"adgroupAwardInfoVO\":{}}}";
       String cuo = "{\"adGroupVO\":{\"contentVOList\":[{\"creativeVOList\":[{\"shunt\":100,\"creativeType\":\"TEXT\",\"creativeDetailVoList\":[{\"variables\":\"{\\\"text\\\":\\\"内容\\\",\\\"title\\\":\\\"标题\\\"}\",\"defaultOrNot\":true,\"language\":\"en_US\"}],\"name\":\"测试\",\"extendInfo\":\"{\\\"isEmpty\\\":false}\"}]}],\"bizType\":\"content_task\",\"campaignId\":\"CAMPAIGN201912101000004559\",\"adGroupScheduleConfig\":{\"excludeDate\":[\"\"],\"periodic\":false,\"periodInfo\":\"{}\"},\"priority\":1,\"eventConfig\":{},\"terminalTargetingList\":[],\"adGroupTimeConfigVO\":{\"endDate\":\"2020-01-04\",\"adGroupTimeConfigDetailVoList\":[{\"startTime\":\"09:11\",\"endTime\":\"20:11\"}],\"startDate\":\"2020-01-02\"},\"fatigueLimitVOList\":[],\"name\":\"任务名称_20200102194954313\",\"deliveryScene\":\"default\",\"adgroupAwardInfoVO\":{},\"adGroupChannel\":{\"resourceCode\":\"TEMPLATE_CODE\",\"channelType\":\"SMS\"},\"ruleTargetingVOList\":[],\"crowdTargetingList\":[{\"fileName\":\"会员域人群(清洗后)10000个用户.txt\",\"gmtModified\":\"2019-12-26 20:05:51\",\"parentTaskName\":\"\",\"bizDate\":\"20191226\",\"userIdType\":\"USER_ID\",\"userProperty\":\"\",\"crowdSource\":\"FILE\",\"crowdId\":\"191226200530888\",\"crowdType\":\"FILE\",\"operatorName\":\"zhangguoxiang@sitTest.com\",\"tableName\":\"\",\"userIdColumn\":\"\",\"sceneId\":\"DEFAULT\",\"expireDate\":\"20201220\",\"crowdDesc\":\"\",\"operatorId\":\"zhangguoxiang@sitTest.com\",\"crowdName\":\"ZGX_已映射_会员域1W用户(清洗后)\",\"ossPath\":\"\",\"crowdStatus\":1,\"storeType\":\"\",\"groupConditions\":[],\"gmtModifierId\":\"zhangguoxiang@sitTest.com\",\"crowdTotalCount\":10000,\"gmtCreate\":\"2019-12-26 20:05:51\",\"fileContentList\":[],\"dataUpdateType\":\"ONCE\",\"gmtModifier\":\"zhangguoxiang@sitTest.com\",\"useSceneType\":\"TARGETING\",\"dataUpdateDate\":\"20191226\",\"tenantId\":\"DANAW3ID\",\"projectName\":\"\",\"principalType\":\"USER_ID\"}]}}";
        System.out.println(JSONObject.parse(dui).equals(JSONObject.parse(cuo)));
//       System.out.println(JSONObject.parse(dui));
//       System.out.println(JSONObject.parse(cuo));

   }

    @Test
    public void jsonDiff2(){
        String jsonData = "{\"singleway\":[],\"multiway\":{\"channelSlave\":[{\"name\":\"aa1\",\"channel0name\":\"dd1\",\"id\":\"1111111113\"},{\"name\":\"aa1\",\"channel0name\":\"dd1\",\"id\":\"1111111112\"}],\"channelMaster\":{\"name\":\"aa\",\"channel0name\":\"dd\",\"id\":\"1111111111\"}}}";
        Map<String, Object> requestMap = readJsonToObject(jsonData, new TypeReference<Map<String, Object>>(){});

        String jsonData2 = "{\"singleway\":[],\"multiway\":{\"channelSlave\":[{\"name\":\"aa1\",\"channel0name\":\"dd2\",\"id\":\"1111111113\"},{\"name\":\"aa1\",\"channel0name\":\"dd1\",\"id\":\"1111111112\"}],\"channelMaster\":{\"name\":\"aa\",\"channel0name\":\"dd\",\"id\":\"1111111111\"}}}";
        Map<String, Object> requestMap2 = readJsonToObject(jsonData2, new TypeReference<Map<String, Object>>(){});

        System.out.println(compareMap(requestMap, requestMap2));
//        System.out.println(JSONObject.parse(cuo));
    }

    public <T> T readJsonToObject(String jsonString, TypeReference<T> tr) {

        ObjectMapper objectMapper = new ObjectMapper();

        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                return (T) objectMapper.readValue(jsonString, tr);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean compareMap(Map<String, Object> leftMap, Map<String, Object> rightMap) {

        MapDifference<String, Object> difference = Maps.difference(leftMap, rightMap);
        //获取所有不同点
        Map<String, MapDifference.ValueDifference<Object>> differenceMap = difference.entriesDiffering();
        Iterator diffIterator = differenceMap.entrySet().iterator();
        if (diffIterator.hasNext()) {
            Map.Entry entry = (java.util.Map.Entry) diffIterator.next();

            MapDifference.ValueDifference<Object> valueDifference = (MapDifference.ValueDifference<Object>) entry.getValue();
            System.out.println("left: " + valueDifference.leftValue());
            System.out.println("right: " + valueDifference.rightValue());

            //处理结果是否为map,则递归执行比较规则
            if (valueDifference.leftValue() instanceof Map && valueDifference.rightValue() instanceof Map) {
                boolean equal = compareMap((Map<String, Object>) valueDifference.leftValue(), (Map<String, Object>) valueDifference.rightValue());
                if (!equal) {
                    return false;
                }
            }
            //如果处理结果为list，则通过list方式处理  - 若list中值相同，但是顺序不同，则认为两个list相同
            if (valueDifference.leftValue() instanceof List && valueDifference.rightValue() instanceof List) {
                boolean equal = ((List) valueDifference.leftValue()).containsAll((List) valueDifference.rightValue());
                if (!equal) {
                    return false;
                }
            }
            //如果处理最终结果为字符串,则停止比较
            if (valueDifference.leftValue() instanceof String && valueDifference.rightValue() instanceof String){
                return false;
            }
        }

        //若B中有A中不存在的值，则认为不同
        Map<String, Object> onlyOnRightMap = difference.entriesOnlyOnRight();
        if (onlyOnRightMap != null && !onlyOnRightMap.isEmpty()){
            return false;
        }

        return true;
    }





}
