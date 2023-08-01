package controller;
import java.lang.reflect.Field;

public class ObjectNullChecker {

    // Phương thức kiểm tra tất cả các thuộc tính của đối tượng có giá trị null hay không
    public static boolean hasNullFields(Object obj) {
        if (obj == null) {
            return true;
        }

        // Sử dụng reflection để lấy danh sách các thuộc tính của đối tượng
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            // Cho phép truy cập vào các thuộc tính riêng tư
            field.setAccessible(true);

            try {
                // Lấy giá trị của thuộc tính
                Object value = field.get(obj);

                // Kiểm tra xem giá trị có là null hay không
                if (value == null) {
                    return true;
                }

                // Nếu thuộc tính là một đối tượng, thực hiện kiểm tra đệ quy
                if (field.getType().isAssignableFrom(Object.class)) {
                    if (hasNullFields(value)) {
                        return true;
                    }
                }
            } catch (IllegalAccessException e) {
                // Xử lý ngoại lệ IllegalAccessException nếu cần thiết
                e.printStackTrace();
            }
        }

        return false;
    }
}

