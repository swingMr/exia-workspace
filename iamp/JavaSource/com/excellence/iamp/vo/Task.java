package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String taskId;//任务id

    private String taskName;//任务名字

    private String taskTime;//任务时间规则
    
    private String taskParams;//任务传递参数

    private int taskType;//任务类型 1-本地定时任务  2-python定时任务 3-远程定时任务
    /**
     * 本地定时任务对应实体类名(com.excellence.iamp.vo.*)
     * 远程定时任务对应远程访问url(http://10.1.6.63:8001/ExIAServer/services/ontology/recognise/concepts)
     */
    private String accessPath;//定时任务对应的执行实体         	  

    private String taskGroup;//任务对应调度池的任务组名
    
    private String triggerName;//任务对应调度池的调度器名
	
	private String triggerGroup;//任务对应调度池的调度器组名

    private String remark;//备注

    /**
     * 1.运行 0 停止 -1禁用 2暂停
     */
    private int status;//任务的运行状态

    private Date createDate;//创建日期

    private String creatorId;//创建人id

    private String creatorName;//创建人名字
    
    private String startTime;//任务开始时间
    
    private String endTime;//任务结束时间

    private Date updateDate;//更新日期

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime == null ? null : taskTime.trim();
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath == null ? null : accessPath.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getTaskParams() {
		return taskParams;
	}

	public void setTaskParams(String taskParams) {
		this.taskParams = taskParams;
	}
}