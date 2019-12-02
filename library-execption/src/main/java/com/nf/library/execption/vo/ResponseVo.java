package com.nf.library.execption.vo;

import lombok.Data;

/**
 *
 * @author Sam
 */
@Data
public class ResponseVo {
    private String code;
    private String msg;
    private Object date;

    private ResponseVo(Builder builder) {
        setCode(builder.code);
        setMsg(builder.msg);
        setDate(builder.date);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String code;
        private String msg;
        private Object date;

        private Builder() {
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder msg(String val) {
            msg = val;
            return this;
        }

        public Builder date(Object val) {
            date = val;
            return this;
        }

        public ResponseVo build() {
            return new ResponseVo(this);
        }
    }
}
