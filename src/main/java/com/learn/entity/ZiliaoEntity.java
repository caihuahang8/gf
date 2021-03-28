package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 资料中心
 */
public class ZiliaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Long id;
        
            //标题
                    private String title;
        
            //地址
                    private String url;
        
            //上传人
                    private Long user;

            private SysUserEntity sysUserEntity;

            public SysUserEntity getSysUserEntity() {
                return sysUserEntity;
            }

            public void setSysUserEntity(SysUserEntity sysUserEntity) {
                this.sysUserEntity = sysUserEntity;
            }

        
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
         * 设置：标题
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * 获取：标题
         */
        public String getTitle() {
            return title;
        }
            /**
         * 设置：地址
         */
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * 获取：地址
         */
        public String getUrl() {
            return url;
        }
            /**
         * 设置：上传人
         */
        public void setUser(Long user) {
            this.user = user;
        }

        /**
         * 获取：上传人
         */
        public Long getUser() {
            return user;
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
