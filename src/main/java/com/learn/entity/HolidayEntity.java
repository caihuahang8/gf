package com.learn.entity;

        import java.io.Serializable;
        import java.util.Date;
        import com.learn.service.*;



/**
 * 学生请假
 */
public class HolidayEntity implements Serializable {
    private static final long serialVersionUID = 1L;

            //
                    private Long id;
        
            //日期
                    private String day;
        
            //请假人
                    private Long user;

            private SysUserEntity sysUserEntity;

            public SysUserEntity getSysUserEntity() {
                return sysUserEntity;
            }

            public void setSysUserEntity(SysUserEntity sysUserEntity) {
                this.sysUserEntity = sysUserEntity;
            }

        
            //原因
                    private String content;
        
            //审批状态
                    private String state;
        
            //说明
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
         * 设置：请假人
         */
        public void setUser(Long user) {
            this.user = user;
        }

        /**
         * 获取：请假人
         */
        public Long getUser() {
            return user;
        }
            /**
         * 设置：原因
         */
        public void setContent(String content) {
            this.content = content;
        }

        /**
         * 获取：原因
         */
        public String getContent() {
            return content;
        }
            /**
         * 设置：审批状态
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         * 获取：审批状态
         */
        public String getState() {
            return state;
        }
            /**
         * 设置：说明
         */
        public void setRemark(String remark) {
            this.remark = remark;
        }

        /**
         * 获取：说明
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
