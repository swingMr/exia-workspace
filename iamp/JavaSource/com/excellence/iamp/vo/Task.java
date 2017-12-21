package com.excellence.iamp.vo;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String taskId;//����id

    private String taskName;//��������

    private String taskTime;//����ʱ�����
    
    private String taskParams;//���񴫵ݲ���

    private int taskType;//�������� 1-���ض�ʱ����  2-python��ʱ���� 3-Զ�̶�ʱ����
    /**
     * ���ض�ʱ�����Ӧʵ������(com.excellence.iamp.vo.*)
     * Զ�̶�ʱ�����ӦԶ�̷���url(http://10.1.6.63:8001/ExIAServer/services/ontology/recognise/concepts)
     */
    private String accessPath;//��ʱ�����Ӧ��ִ��ʵ��         	  

    private String taskGroup;//�����Ӧ���ȳص���������
    
    private String triggerName;//�����Ӧ���ȳصĵ�������
	
	private String triggerGroup;//�����Ӧ���ȳصĵ���������

    private String remark;//��ע

    /**
     * 1.���� 0 ֹͣ -1���� 2��ͣ
     */
    private int status;//���������״̬

    private Date createDate;//��������

    private String creatorId;//������id

    private String creatorName;//����������
    
    private String startTime;//����ʼʱ��
    
    private String endTime;//�������ʱ��

    private Date updateDate;//��������

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