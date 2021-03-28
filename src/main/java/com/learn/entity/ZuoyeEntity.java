package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 作业信息
 */
public class ZuoyeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Long id;
        
            //名称
                    private String name;
        
            //内容
                    private String content;
        
            //文件
                    private String url;
        
            //结束时间
                    private Date end;
        
            //备注
                    private String remark;
        
            //添加时间
                    private Date gmttime =new  Date();
        
    
            /**
         * 设置：
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * 获取：
         */
        public Long getId() {
            return id;
        }
            /**
         * 设置：名称
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * 获取：名称
         */
        public String getName() {
            return name;
        }
            /**
         * 设置：内容
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * 获取：内容
         */
        public String getContent() {
            return content;
        }
            /**
         * 设置：文件
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * 获取：文件
         */
        public String getUrl() {
            return url;
        }
            /**
         * 设置：结束时间
         */
        public void setEnd(Date end) {
            this.end = end;
        }

        /**
         * 获取：结束时间
         */
        public Date getEnd() {
            return end;
        }
            /**
         * 设置：备注
         */
        public void setRemark(String remark) {
            this.remark = remark;
        }

        /**
         * 获取：备注
         */
        public String getRemark() {
            return remark;
        }
            /**
         * 设置：添加时间
         */
        public void setGmttime(Date gmttime) {
            this.gmttime = gmttime;
        }

        /**
         * 获取：添加时间
         */
        public Date getGmttime() {
            return gmttime;
        }
    }
