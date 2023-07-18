package com.zsc.example.nobody.diff;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.*;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2022-10-13 11:37
 * @Copyright: HOSE合思｜易快报
 **/
public class JsonDiff2 {


//    public static boolean areEqual(Object ob1, Object ob2)  {
//        Object obj1Converted = convertJsonElement(ob1);
//        Object obj2Converted = convertJsonElement(ob2);
//        return obj1Converted.equals(obj2Converted);
//    }

//    private static Object convertJsonElement(Object elem) {
//        if (elem instanceof JSONObject) {
//            JSONObject obj = (JSONObject) elem;
//            Iterator<String> keys = obj.keys();
//            Map<String, Object> jsonMap = new HashMap<>();
//            while (keys.hasNext()) {
//                String key = keys.next();
//                jsonMap.put(key, convertJsonElement(obj.get(key)));
//            }
//            return jsonMap;
//        } else if (elem instanceof JSONArray) {
//            JSONArray arr = (JSONArray) elem;
//            Set<Object> jsonSet = new HashSet<>();
//            for (int i = 0; i < arr.length(); i++) {
//                jsonSet.add(convertJsonElement(arr.get(i)));
//            }
//            return jsonSet;
//        } else {
//            return elem;
//        }
//    }

    @Test
    public void jsonDiff2(){
        String dui = "{\"adGroupVO\":{\"campaignId\":\"CAMPAIGN201912101000004559\",\"adGroupChannel\":{\"channelType\":\"SMS\",\"resourceCode\":\"TEMPLATE_CODE\"},\"deliveryScene\":\"default\",\"adGroupTimeConfigVO\":{\"startDate\":\"2020-01-02\",\"endDate\":\"2020-01-04\",\"adGroupTimeConfigDetailVoList\":[{\"endTime\":\"20:11\",\"startTime\":\"09:11\"}]},\"adGroupScheduleConfig\":{\"periodic\":false,\"excludeDate\":[\"\"],\"periodInfo\":\"{}\"},\"eventConfig\":{},\"crowdTargetingList\":[{\"bizDate\":\"20191226\",\"principalType\":\"USER_ID\",\"crowdDesc\":\"\",\"crowdId\":\"191226200530888\",\"crowdName\":\"ZGX_已映射_会员域1W用户(清洗后)\",\"crowdSource\":\"FILE\",\"crowdStatus\":1,\"crowdTotalCount\":10000,\"crowdType\":\"FILE\",\"dataUpdateDate\":\"20191226\",\"dataUpdateType\":\"ONCE\",\"expireDate\":\"20201220\",\"fileContentList\":[],\"fileName\":\"会员域人群(清洗后)10000个用户.txt\",\"gmtCreate\":\"2019-12-26 20:05:51\",\"gmtModified\":\"2019-12-26 20:05:51\",\"gmtModifier\":\"zhangguoxiang@sitTest.com\",\"gmtModifierId\":\"zhangguoxiang@sitTest.com\",\"groupConditions\":[],\"operatorId\":\"zhangguoxiang@sitTest.com\",\"operatorName\":\"zhangguoxiang@sitTest.com\",\"ossPath\":\"\",\"parentTaskName\":\"\",\"projectName\":\"\",\"sceneId\":\"DEFAULT\",\"storeType\":\"\",\"tableName\":\"\",\"tenantId\":\"DANAW3ID\",\"userIdColumn\":\"\",\"userIdType\":\"USER_ID\",\"userProperty\":\"\",\"useSceneType\":\"TARGETING\"}],\"fatigueLimitVOList\":[],\"name\":\"任务名称_20200102194954313\",\"priority\":1,\"ruleTargetingVOList\":[],\"terminalTargetingList\":[],\"contentVOList\":[{\"creativeVOList\":[{\"name\":\"测试\",\"creativeType\":\"TEXT\",\"creativeDetailVoList\":[{\"language\":\"en_US\",\"defaultOrNot\":true,\"variables\":\"{\\\"text\\\":\\\"内容\\\",\\\"title\\\":\\\"标题\\\"}\"}],\"extendInfo\":\"{\\\"isEmpty\\\":false}\",\"shunt\":100}]}],\"bizType\":\"content_task\",\"adgroupAwardInfoVO\":{}}}";
        String cuo = "{\"adGroupVO\":{\"contentVOList\":[{\"creativeVOList\":[{\"shunt\":10,\"creativeType\":\"TEXT\",\"creativeDetailVoList\":[{\"variables\":\"{\\\"text\\\":\\\"内容\\\",\\\"title\\\":\\\"标题\\\"}\",\"defaultOrNot\":true,\"language\":\"en_US\"}],\"name\":\"测试\",\"extendInfo\":\"{\\\"isEmpty\\\":false}\"}]}],\"bizType\":\"content_task\",\"campaignId\":\"CAMPAIGN201912101000004559\",\"adGroupScheduleConfig\":{\"excludeDate\":[\"\"],\"periodic\":false,\"periodInfo\":\"{}\"},\"priority\":1,\"eventConfig\":{},\"terminalTargetingList\":[],\"adGroupTimeConfigVO\":{\"endDate\":\"2020-01-04\",\"adGroupTimeConfigDetailVoList\":[{\"startTime\":\"09:11\",\"endTime\":\"20:11\"}],\"startDate\":\"2020-01-02\"},\"fatigueLimitVOList\":[],\"name\":\"任务名称_20200102194954313\",\"deliveryScene\":\"default\",\"adgroupAwardInfoVO\":{},\"adGroupChannel\":{\"resourceCode\":\"TEMPLATE_CODE\",\"channelType\":\"SMS\"},\"ruleTargetingVOList\":[],\"crowdTargetingList\":[{\"fileName\":\"会员域人群(清洗后)10000个用户.txt\",\"gmtModified\":\"2019-12-26 20:05:51\",\"parentTaskName\":\"\",\"bizDate\":\"20191226\",\"userIdType\":\"USER_ID\",\"userProperty\":\"\",\"crowdSource\":\"FILE\",\"crowdId\":\"191226200530888\",\"crowdType\":\"FILE\",\"operatorName\":\"zhangguoxiang@sitTest.com\",\"tableName\":\"\",\"userIdColumn\":\"\",\"sceneId\":\"DEFAULT\",\"expireDate\":\"20201220\",\"crowdDesc\":\"\",\"operatorId\":\"zhangguoxiang@sitTest.com\",\"crowdName\":\"ZGX_已映射_会员域1W用户(清洗后)\",\"ossPath\":\"\",\"crowdStatus\":1,\"storeType\":\"\",\"groupConditions\":[],\"gmtModifierId\":\"zhangguoxiang@sitTest.com\",\"crowdTotalCount\":10000,\"gmtCreate\":\"2019-12-26 20:05:51\",\"fileContentList\":[],\"dataUpdateType\":\"ONCE\",\"gmtModifier\":\"zhangguoxiang@sitTest.com\",\"useSceneType\":\"TARGETING\",\"dataUpdateDate\":\"20191226\",\"tenantId\":\"DANAW3ID\",\"projectName\":\"\",\"principalType\":\"USER_ID\"}]}}";
        JsonObject json=new JsonParser().parse(dui).getAsJsonObject();
        JsonObject json2=new JsonParser().parse(cuo).getAsJsonObject();
        System.out.println(compareJson(json, json2));

//        compareJson
    }
//channelType
    private boolean compareJson(JsonElement json1, JsonElement json2) {
        boolean isEqual = true;
        if (json1 != null && json2 != null) {
            if (json1.isJsonObject() && json2.isJsonObject()) {
                Set<Map.Entry<String, JsonElement>> ens1 = ((JsonObject) json1).entrySet();
                Set<Map.Entry<String, JsonElement>> ens2 = ((JsonObject) json2).entrySet();
                JsonObject json2obj = (JsonObject) json2;
                if (ens1 != null && ens2 != null) {
                    for (Map.Entry<String, JsonElement> en : ens1) {
                        isEqual = isEqual && compareJson(en.getValue(), json2obj.get(en.getKey()));
                    }
                } else {
                    return false;
                }
            }

            // Check whether both jsonElement are arrays
            else if (json1.isJsonArray() && json2.isJsonArray()) {
                JsonArray jarr1 = json1.getAsJsonArray();
                JsonArray jarr2 = json2.getAsJsonArray();
                if (jarr1.size() != jarr2.size()) {
                    return false;
                } else {
                    int i = 0;
                    // Iterate JSON Array to JSON Elements
                    for (JsonElement je : jarr1) {
                        isEqual = isEqual && compareJson(je, jarr2.get(i));
                        i++;
                    }
                }
            }

            // Check whether both jsonElement are null
            else if (json1.isJsonNull() && json2.isJsonNull()) {
                return true;
            }

            // Check whether both jsonElement are primitives
            else if (json1.isJsonPrimitive() && json2.isJsonPrimitive()) {
                if (json1.equals(json2)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (json1 == null && json2 == null) {
            return true;
        } else {
            return false;
        }
        return isEqual;
    }
}
