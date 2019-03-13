package com.example.plaso.customview.dataBean;

public class UploadFaceBean {
    /**
     * 上传头像图像后返回的json
     * 文档地址https://api.cmfapp.co.uk/cmfcms/documentation.php
     * code : 100
     * data : {"fromServer":true,"curl_error":false,"face_detection_result":true,"face_detection_apis_not_responding":false,"line":280}
     * success : true
     * session_id : e0d54b84cb0f95a3c27716fdb4aa4947
     * message : Image uploaded successfully
     */
    private int code;
    private DataEntity data;
    private boolean success;
    private String session_id;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
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

    public boolean isSuccess() {
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
         * fromServer : true
         * curl_error : false
         * face_detection_result : true
         * face_detection_apis_not_responding : false
         * line : 280
         */
        private boolean fromServer;
        private boolean curl_error;
        private boolean face_detection_result;
        private boolean face_detection_apis_not_responding;
        private int line;

        public void setFromServer(boolean fromServer) {
            this.fromServer = fromServer;
        }

        public void setCurl_error(boolean curl_error) {
            this.curl_error = curl_error;
        }

        public void setFace_detection_result(boolean face_detection_result) {
            this.face_detection_result = face_detection_result;
        }

        public void setFace_detection_apis_not_responding(boolean face_detection_apis_not_responding) {
            this.face_detection_apis_not_responding = face_detection_apis_not_responding;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public boolean isFromServer() {
            return fromServer;
        }

        public boolean isCurl_error() {
            return curl_error;
        }

        public boolean isFace_detection_result() {
            return face_detection_result;
        }

        public boolean isFace_detection_apis_not_responding() {
            return face_detection_apis_not_responding;
        }

        public int getLine() {
            return line;
        }
    }
}
