package com.zsc.example.nobody.geodesy;
 
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
 
public class test0 {
    public static void main(String[] args)
    {
        // //121.717594,31.12055    121.817629,31.090867
        GlobalCoordinates source = new GlobalCoordinates(39.904030, 116.407526);
//        GlobalCoordinates target = new GlobalCoordinates(34.746599, 113.625368);
        GlobalCoordinates target = new GlobalCoordinates(39.904030, 116.407626);
 
        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
 
        System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        System.out.println("WGS84坐标系计算结果："+meter2 + "米");
    }
 
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid)
    {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
 
        return geoCurve.getEllipsoidalDistance();
    }


}

