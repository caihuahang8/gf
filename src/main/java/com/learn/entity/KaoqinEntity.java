package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 考勤记录
 */
public class KaoqinEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Long id;
        
            //日期
                    private String day;
        
            //学生
                    private Long user;

            private SysUserEntity sysUserEntity;

            public SysUserEntity getSysUserEntity() {
                return sysUserEntity;
            }

            public void setSysUserEntity(SysUserEntity sysUserEntity) {
                this.sysUserEntity = sysUserEntity;
            }

        
            //状态
                    private String state;
        
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
         * 设置：日期
         */
        public void setDay(String day) {
            this.day = day;
        }

        /**
         * 获取：日期
         */
        public String getDay() {
            return day;
        }
            /**
         * 设置：学生
         */
        public void setUser(Long user) {
            this.user = user;
        }

        /**
         * 获取：学生
         */
        public Long getUser() {
            return user;
        }
            /**
         * 设置：状态
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         * 获取：状态
         */
        public String getState() {
            return state;
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
