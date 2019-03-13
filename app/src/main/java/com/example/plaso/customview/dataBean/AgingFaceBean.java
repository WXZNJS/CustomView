package com.example.plaso.customview.dataBean;

import java.util.List;

public class AgingFaceBean {

    /**
     * 老化处理效果图
     * code : 102
     * data : {"fromServer":1,"effect_results":[{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/5e7b2.jpeg","category":null,"age":25,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/deccf.jpeg","category":null,"age":30,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/1490e.jpeg","category":null,"age":35,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/ca005.jpeg","category":null,"age":40,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/0fc9f.jpeg","category":null,"age":45,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/c6c15.jpeg","category":null,"age":50,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/8f7d4.jpeg","category":null,"age":55,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/305c5.jpeg","category":null,"age":60,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/55fff.jpeg","category":null,"age":65,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/4c38f.jpeg","category":null,"age":70,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/c684e.jpeg","category":null,"age":75,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/709cb.jpeg","category":null,"age":80,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/b41ae.jpeg","category":null,"age":85,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/b6007.jpeg","category":null,"age":90,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/b166f.jpeg","category":null,"age":95,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/03b0f.jpeg","category":null,"age":100,"effect_id":100}]}
     * success : 1
     * session_id : e07eb67f6051804f67a0568b8192d374
     * message : Effects applied successfully
     */
    private int code;
    private DataEntity data;
    private int success;
    private String session_id;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public int getSuccess() {
        return success;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getMessage() {
        return message;
    }

    public class DataEntity {
        /**
         * fromServer : 1
         * effect_results : [{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/5e7b2.jpeg","category":null,"age":25,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/deccf.jpeg","category":null,"age":30,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/1490e.jpeg","category":null,"age":35,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/ca005.jpeg","category":null,"age":40,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/0fc9f.jpeg","category":null,"age":45,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/c6c15.jpeg","category":null,"age":50,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/8f7d4.jpeg","category":null,"age":55,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/305c5.jpeg","category":null,"age":60,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/55fff.jpeg","category":null,"age":65,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/4c38f.jpeg","category":null,"age":70,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/c684e.jpeg","category":null,"age":75,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/709cb.jpeg","category":null,"age":80,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/b41ae.jpeg","category":null,"age":85,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/b6007.jpeg","category":null,"age":90,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/b166f.jpeg","category":null,"age":95,"effect_id":100},{"success":1,"output_file":"http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/03b0f.jpeg","category":null,"age":100,"effect_id":100}]
         */
        private int fromServer;
        private List<Effect_resultsEntity> effect_results;

        public void setFromServer(int fromServer) {
            this.fromServer = fromServer;
        }

        public void setEffect_results(List<Effect_resultsEntity> effect_results) {
            this.effect_results = effect_results;
        }

        public int getFromServer() {
            return fromServer;
        }

        public List<Effect_resultsEntity> getEffect_results() {
            return effect_results;
        }

        public class Effect_resultsEntity {
            /**
             * success : 1
             * output_file : http://api.cmfapp.co.uk/api2/images/e07eb67f6051804f67a0568b8192d374/5e7b2.jpeg
             * category : null
             * age : 25
             * effect_id : 100
             */
            private int success;
            private String output_file;
            private String category;
            private int age;
            private int effect_id;

            public void setSuccess(int success) {
                this.success = success;
            }

            public void setOutput_file(String output_file) {
                this.output_file = output_file;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public void setEffect_id(int effect_id) {
                this.effect_id = effect_id;
            }

            public int getSuccess() {
                return success;
            }

            public String getOutput_file() {
                return output_file;
            }

            public String getCategory() {
                return category;
            }

            public int getAge() {
                return age;
            }

            public int getEffect_id() {
                return effect_id;
            }
        }
    }
}
