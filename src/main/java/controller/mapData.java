//package controller;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class mapData {
//    public static String[][] expandArray(String[][] inputArray){
//
//        List<Object[]> oldDataList = Arrays.asList(inputArray);
//        List<Object[]> newDataList = new ArrayList<>();
//        oldDataList.stream().forEach(s->{
//            int n = countElements((String) s[3],",");
//            // Tách chuỗi thành mảng các trái cây dựa vào ký tự phân cách ","
////            cắt cột 3
//            String[] collum3 = ((String) s[3]).split(",");
//            String[] collum4 = ((String) s[4]).split(",");
//
//            if (n>1){
//                for (int i = 0;i<n;i++){
//
//                    newDataList.add()
//                }
//            }
//        });
//
//
//
//
//
//
//
//        return resultArray;
//    }
//
//    public static int countElements(String inputString, String delimiter) {
//        if (inputString == null || inputString.isEmpty()) {
//            return 0;
//        }
//
//        String[] elements = inputString.split("\\Q" + delimiter + "\\E");
//        return elements.length;
//    }
//
//
//}
