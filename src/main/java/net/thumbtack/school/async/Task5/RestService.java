package net.thumbtack.school.async.Task5;

import com.google.gson.Gson;

public class RestService {
    private Gson gson = new Gson();

    public String process(String inputJson) {
        XY xy = gson.fromJson(inputJson, XY.class);
        int result = xy.getX() + xy.getY();
        System.out.println(result);
        return "{\"result\": " + result + "}";
    }

    class XY {
        int x;
        int y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

}