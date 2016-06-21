package com.ws.model;

import java.util.Date;
import java.util.List;

public class AlarmData {
    private Long alarmDataId;
    

    private Long kpiId;

    private Long nodeId;

    private Integer regionId;

    private Long perfDataId;

    private Long nodeConfigId;

    private String nodeConfigName;

    private String kpiValue;

    private String alarmType;

    private String alarmSource;

    private String alarmRegionSource;

    private String alarmClass;

    private String alarmLevel;

    private Integer alarmCount;

    private Long mergeAlarmMsgId;

    private String mergeReason;

    private Date createDate;

    private Date receiveDate;

    private Date dealDate;

    private String state;

    private Date stateDate;

    private Integer drId;

    private String infoRemark;

    private Date lastSendTime;

    private Integer confirmOperator;

    private Date confirmTime;

    private Date deleteTime;

    private Integer deleteOperator;

    private Integer clearOperator;

    private Date clearTime;

    private String oprtState;

    private String alarmState;

    private String msgRemark;

    private String oprtResult;

    private Long flowId;

    private String flowOperator;

    private Date lastGenerateTime;

    private Short makeAlarmRegion;

    private String expertAdvice;

    private String matchRules;

    private String proPheno;

    private String proReason;

    private String measures;

    private Date suspendTime;

    private Integer suspendOperator;

    private String oriAlarmType;

    private String oriAlarmLevel;

    private String alarmTitle;

    private Integer nodeTypeId;

    private String nodeTypeName;

    private String nodeName;

    private Date generateTime;

    private Date lastModifyTime;

    private Short dispatchStatus;
    private String kpiName;
	private String regionName;

    private List<Long> alarmDataIds;
	
    public Long getAlarmDataId() {
        return alarmDataId;
    }

    public void setAlarmDataId(Long alarmDataId) {
        this.alarmDataId = alarmDataId;
    }

    public Long getKpiId() {
        return kpiId;
    }

    public void setKpiId(Long kpiId) {
        this.kpiId = kpiId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Long getPerfDataId() {
        return perfDataId;
    }

    public void setPerfDataId(Long perfDataId) {
        this.perfDataId = perfDataId;
    }

    public Long getNodeConfigId() {
        return nodeConfigId;
    }

    public void setNodeConfigId(Long nodeConfigId) {
        this.nodeConfigId = nodeConfigId;
    }

    public String getNodeConfigName() {
        return nodeConfigName;
    }

    public void setNodeConfigName(String nodeConfigName) {
        this.nodeConfigName = nodeConfigName == null ? null : nodeConfigName.trim();
    }

    public String getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(String kpiValue) {
        this.kpiValue = kpiValue == null ? null : kpiValue.trim();
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType == null ? null : alarmType.trim();
    }

    public String getAlarmSource() {
        return alarmSource;
    }

    public void setAlarmSource(String alarmSource) {
        this.alarmSource = alarmSource == null ? null : alarmSource.trim();
    }

    public String getAlarmRegionSource() {
        return alarmRegionSource;
    }

    public void setAlarmRegionSource(String alarmRegionSource) {
        this.alarmRegionSource = alarmRegionSource == null ? null : alarmRegionSource.trim();
    }

    public String getAlarmClass() {
        return alarmClass;
    }

    public void setAlarmClass(String alarmClass) {
        this.alarmClass = alarmClass == null ? null : alarmClass.trim();
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel == null ? null : alarmLevel.trim();
    }

    public Integer getAlarmCount() {
        return alarmCount;
    }

    public void setAlarmCount(Integer alarmCount) {
        this.alarmCount = alarmCount;
    }

    public Long getMergeAlarmMsgId() {
        return mergeAlarmMsgId;
    }

    public void setMergeAlarmMsgId(Long mergeAlarmMsgId) {
        this.mergeAlarmMsgId = mergeAlarmMsgId;
    }

    public String getMergeReason() {
        return mergeReason;
    }

    public void setMergeReason(String mergeReason) {
        this.mergeReason = mergeReason == null ? null : mergeReason.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public String getInfoRemark() {
        return infoRemark;
    }

    public void setInfoRemark(String infoRemark) {
        this.infoRemark = infoRemark == null ? null : infoRemark.trim();
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Integer getConfirmOperator() {
        return confirmOperator;
    }

    public void setConfirmOperator(Integer confirmOperator) {
        this.confirmOperator = confirmOperator;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteOperator() {
        return deleteOperator;
    }

    public void setDeleteOperator(Integer deleteOperator) {
        this.deleteOperator = deleteOperator;
    }

    public Integer getClearOperator() {
        return clearOperator;
    }

    public void setClearOperator(Integer clearOperator) {
        this.clearOperator = clearOperator;
    }

    public Date getClearTime() {
        return clearTime;
    }

    public void setClearTime(Date clearTime) {
        this.clearTime = clearTime;
    }

    public String getOprtState() {
        return oprtState;
    }

    public void setOprtState(String oprtState) {
        this.oprtState = oprtState == null ? null : oprtState.trim();
    }

    public String getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(String alarmState) {
        this.alarmState = alarmState == null ? null : alarmState.trim();
    }

    public String getMsgRemark() {
        return msgRemark;
    }

    public void setMsgRemark(String msgRemark) {
        this.msgRemark = msgRemark == null ? null : msgRemark.trim();
    }

    public String getOprtResult() {
        return oprtResult;
    }

    public void setOprtResult(String oprtResult) {
        this.oprtResult = oprtResult == null ? null : oprtResult.trim();
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public String getFlowOperator() {
        return flowOperator;
    }

    public void setFlowOperator(String flowOperator) {
        this.flowOperator = flowOperator == null ? null : flowOperator.trim();
    }

    public Date getLastGenerateTime() {
        return lastGenerateTime;
    }

    public void setLastGenerateTime(Date lastGenerateTime) {
        this.lastGenerateTime = lastGenerateTime;
    }

    public Short getMakeAlarmRegion() {
        return makeAlarmRegion;
    }

    public void setMakeAlarmRegion(Short makeAlarmRegion) {
        this.makeAlarmRegion = makeAlarmRegion;
    }

    public String getExpertAdvice() {
        return expertAdvice;
    }

    public void setExpertAdvice(String expertAdvice) {
        this.expertAdvice = expertAdvice == null ? null : expertAdvice.trim();
    }

    public String getMatchRules() {
        return matchRules;
    }

    public void setMatchRules(String matchRules) {
        this.matchRules = matchRules == null ? null : matchRules.trim();
    }

    public String getProPheno() {
        return proPheno;
    }

    public void setProPheno(String proPheno) {
        this.proPheno = proPheno == null ? null : proPheno.trim();
    }

    public String getProReason() {
        return proReason;
    }

    public void setProReason(String proReason) {
        this.proReason = proReason == null ? null : proReason.trim();
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    public Date getSuspendTime() {
        return suspendTime;
    }

    public void setSuspendTime(Date suspendTime) {
        this.suspendTime = suspendTime;
    }

    public Integer getSuspendOperator() {
        return suspendOperator;
    }

    public void setSuspendOperator(Integer suspendOperator) {
        this.suspendOperator = suspendOperator;
    }

    public String getOriAlarmType() {
        return oriAlarmType;
    }

    public void setOriAlarmType(String oriAlarmType) {
        this.oriAlarmType = oriAlarmType == null ? null : oriAlarmType.trim();
    }

    public String getOriAlarmLevel() {
        return oriAlarmLevel;
    }

    public void setOriAlarmLevel(String oriAlarmLevel) {
        this.oriAlarmLevel = oriAlarmLevel == null ? null : oriAlarmLevel.trim();
    }

    public String getAlarmTitle() {
        return alarmTitle;
    }

    public void setAlarmTitle(String alarmTitle) {
        this.alarmTitle = alarmTitle == null ? null : alarmTitle.trim();
    }

    public Integer getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Integer nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName == null ? null : nodeTypeName.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Short getDispatchStatus() {
        return dispatchStatus;
    }

    public void setDispatchStatus(Short dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }

    
    public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public List<Long> getAlarmDataIds() {
		return alarmDataIds;
	}

	public void setAlarmDataIds(List<Long> alarmDataIds) {
		this.alarmDataIds = alarmDataIds;
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AlarmData other = (AlarmData) that;
        return (this.getAlarmDataId() == null ? other.getAlarmDataId() == null : this.getAlarmDataId().equals(other.getAlarmDataId()))
            && (this.getKpiId() == null ? other.getKpiId() == null : this.getKpiId().equals(other.getKpiId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getRegionId() == null ? other.getRegionId() == null : this.getRegionId().equals(other.getRegionId()))
            && (this.getPerfDataId() == null ? other.getPerfDataId() == null : this.getPerfDataId().equals(other.getPerfDataId()))
            && (this.getNodeConfigId() == null ? other.getNodeConfigId() == null : this.getNodeConfigId().equals(other.getNodeConfigId()))
            && (this.getNodeConfigName() == null ? other.getNodeConfigName() == null : this.getNodeConfigName().equals(other.getNodeConfigName()))
            && (this.getKpiValue() == null ? other.getKpiValue() == null : this.getKpiValue().equals(other.getKpiValue()))
            && (this.getAlarmType() == null ? other.getAlarmType() == null : this.getAlarmType().equals(other.getAlarmType()))
            && (this.getAlarmSource() == null ? other.getAlarmSource() == null : this.getAlarmSource().equals(other.getAlarmSource()))
            && (this.getAlarmRegionSource() == null ? other.getAlarmRegionSource() == null : this.getAlarmRegionSource().equals(other.getAlarmRegionSource()))
            && (this.getAlarmClass() == null ? other.getAlarmClass() == null : this.getAlarmClass().equals(other.getAlarmClass()))
            && (this.getAlarmLevel() == null ? other.getAlarmLevel() == null : this.getAlarmLevel().equals(other.getAlarmLevel()))
            && (this.getAlarmCount() == null ? other.getAlarmCount() == null : this.getAlarmCount().equals(other.getAlarmCount()))
            && (this.getMergeAlarmMsgId() == null ? other.getMergeAlarmMsgId() == null : this.getMergeAlarmMsgId().equals(other.getMergeAlarmMsgId()))
            && (this.getMergeReason() == null ? other.getMergeReason() == null : this.getMergeReason().equals(other.getMergeReason()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getReceiveDate() == null ? other.getReceiveDate() == null : this.getReceiveDate().equals(other.getReceiveDate()))
            && (this.getDealDate() == null ? other.getDealDate() == null : this.getDealDate().equals(other.getDealDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()))
            && (this.getDrId() == null ? other.getDrId() == null : this.getDrId().equals(other.getDrId()))
            && (this.getInfoRemark() == null ? other.getInfoRemark() == null : this.getInfoRemark().equals(other.getInfoRemark()))
            && (this.getLastSendTime() == null ? other.getLastSendTime() == null : this.getLastSendTime().equals(other.getLastSendTime()))
            && (this.getConfirmOperator() == null ? other.getConfirmOperator() == null : this.getConfirmOperator().equals(other.getConfirmOperator()))
            && (this.getConfirmTime() == null ? other.getConfirmTime() == null : this.getConfirmTime().equals(other.getConfirmTime()))
            && (this.getDeleteTime() == null ? other.getDeleteTime() == null : this.getDeleteTime().equals(other.getDeleteTime()))
            && (this.getDeleteOperator() == null ? other.getDeleteOperator() == null : this.getDeleteOperator().equals(other.getDeleteOperator()))
            && (this.getClearOperator() == null ? other.getClearOperator() == null : this.getClearOperator().equals(other.getClearOperator()))
            && (this.getClearTime() == null ? other.getClearTime() == null : this.getClearTime().equals(other.getClearTime()))
            && (this.getOprtState() == null ? other.getOprtState() == null : this.getOprtState().equals(other.getOprtState()))
            && (this.getAlarmState() == null ? other.getAlarmState() == null : this.getAlarmState().equals(other.getAlarmState()))
            && (this.getMsgRemark() == null ? other.getMsgRemark() == null : this.getMsgRemark().equals(other.getMsgRemark()))
            && (this.getOprtResult() == null ? other.getOprtResult() == null : this.getOprtResult().equals(other.getOprtResult()))
            && (this.getFlowId() == null ? other.getFlowId() == null : this.getFlowId().equals(other.getFlowId()))
            && (this.getFlowOperator() == null ? other.getFlowOperator() == null : this.getFlowOperator().equals(other.getFlowOperator()))
            && (this.getLastGenerateTime() == null ? other.getLastGenerateTime() == null : this.getLastGenerateTime().equals(other.getLastGenerateTime()))
            && (this.getMakeAlarmRegion() == null ? other.getMakeAlarmRegion() == null : this.getMakeAlarmRegion().equals(other.getMakeAlarmRegion()))
            && (this.getExpertAdvice() == null ? other.getExpertAdvice() == null : this.getExpertAdvice().equals(other.getExpertAdvice()))
            && (this.getMatchRules() == null ? other.getMatchRules() == null : this.getMatchRules().equals(other.getMatchRules()))
            && (this.getProPheno() == null ? other.getProPheno() == null : this.getProPheno().equals(other.getProPheno()))
            && (this.getProReason() == null ? other.getProReason() == null : this.getProReason().equals(other.getProReason()))
            && (this.getMeasures() == null ? other.getMeasures() == null : this.getMeasures().equals(other.getMeasures()))
            && (this.getSuspendTime() == null ? other.getSuspendTime() == null : this.getSuspendTime().equals(other.getSuspendTime()))
            && (this.getSuspendOperator() == null ? other.getSuspendOperator() == null : this.getSuspendOperator().equals(other.getSuspendOperator()))
            && (this.getOriAlarmType() == null ? other.getOriAlarmType() == null : this.getOriAlarmType().equals(other.getOriAlarmType()))
            && (this.getOriAlarmLevel() == null ? other.getOriAlarmLevel() == null : this.getOriAlarmLevel().equals(other.getOriAlarmLevel()))
            && (this.getAlarmTitle() == null ? other.getAlarmTitle() == null : this.getAlarmTitle().equals(other.getAlarmTitle()))
            && (this.getNodeTypeId() == null ? other.getNodeTypeId() == null : this.getNodeTypeId().equals(other.getNodeTypeId()))
            && (this.getNodeTypeName() == null ? other.getNodeTypeName() == null : this.getNodeTypeName().equals(other.getNodeTypeName()))
            && (this.getNodeName() == null ? other.getNodeName() == null : this.getNodeName().equals(other.getNodeName()))
            && (this.getGenerateTime() == null ? other.getGenerateTime() == null : this.getGenerateTime().equals(other.getGenerateTime()))
            && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
            && (this.getDispatchStatus() == null ? other.getDispatchStatus() == null : this.getDispatchStatus().equals(other.getDispatchStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAlarmDataId() == null) ? 0 : getAlarmDataId().hashCode());
        result = prime * result + ((getKpiId() == null) ? 0 : getKpiId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getRegionId() == null) ? 0 : getRegionId().hashCode());
        result = prime * result + ((getPerfDataId() == null) ? 0 : getPerfDataId().hashCode());
        result = prime * result + ((getNodeConfigId() == null) ? 0 : getNodeConfigId().hashCode());
        result = prime * result + ((getNodeConfigName() == null) ? 0 : getNodeConfigName().hashCode());
        result = prime * result + ((getKpiValue() == null) ? 0 : getKpiValue().hashCode());
        result = prime * result + ((getAlarmType() == null) ? 0 : getAlarmType().hashCode());
        result = prime * result + ((getAlarmSource() == null) ? 0 : getAlarmSource().hashCode());
        result = prime * result + ((getAlarmRegionSource() == null) ? 0 : getAlarmRegionSource().hashCode());
        result = prime * result + ((getAlarmClass() == null) ? 0 : getAlarmClass().hashCode());
        result = prime * result + ((getAlarmLevel() == null) ? 0 : getAlarmLevel().hashCode());
        result = prime * result + ((getAlarmCount() == null) ? 0 : getAlarmCount().hashCode());
        result = prime * result + ((getMergeAlarmMsgId() == null) ? 0 : getMergeAlarmMsgId().hashCode());
        result = prime * result + ((getMergeReason() == null) ? 0 : getMergeReason().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getReceiveDate() == null) ? 0 : getReceiveDate().hashCode());
        result = prime * result + ((getDealDate() == null) ? 0 : getDealDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStateDate() == null) ? 0 : getStateDate().hashCode());
        result = prime * result + ((getDrId() == null) ? 0 : getDrId().hashCode());
        result = prime * result + ((getInfoRemark() == null) ? 0 : getInfoRemark().hashCode());
        result = prime * result + ((getLastSendTime() == null) ? 0 : getLastSendTime().hashCode());
        result = prime * result + ((getConfirmOperator() == null) ? 0 : getConfirmOperator().hashCode());
        result = prime * result + ((getConfirmTime() == null) ? 0 : getConfirmTime().hashCode());
        result = prime * result + ((getDeleteTime() == null) ? 0 : getDeleteTime().hashCode());
        result = prime * result + ((getDeleteOperator() == null) ? 0 : getDeleteOperator().hashCode());
        result = prime * result + ((getClearOperator() == null) ? 0 : getClearOperator().hashCode());
        result = prime * result + ((getClearTime() == null) ? 0 : getClearTime().hashCode());
        result = prime * result + ((getOprtState() == null) ? 0 : getOprtState().hashCode());
        result = prime * result + ((getAlarmState() == null) ? 0 : getAlarmState().hashCode());
        result = prime * result + ((getMsgRemark() == null) ? 0 : getMsgRemark().hashCode());
        result = prime * result + ((getOprtResult() == null) ? 0 : getOprtResult().hashCode());
        result = prime * result + ((getFlowId() == null) ? 0 : getFlowId().hashCode());
        result = prime * result + ((getFlowOperator() == null) ? 0 : getFlowOperator().hashCode());
        result = prime * result + ((getLastGenerateTime() == null) ? 0 : getLastGenerateTime().hashCode());
        result = prime * result + ((getMakeAlarmRegion() == null) ? 0 : getMakeAlarmRegion().hashCode());
        result = prime * result + ((getExpertAdvice() == null) ? 0 : getExpertAdvice().hashCode());
        result = prime * result + ((getMatchRules() == null) ? 0 : getMatchRules().hashCode());
        result = prime * result + ((getProPheno() == null) ? 0 : getProPheno().hashCode());
        result = prime * result + ((getProReason() == null) ? 0 : getProReason().hashCode());
        result = prime * result + ((getMeasures() == null) ? 0 : getMeasures().hashCode());
        result = prime * result + ((getSuspendTime() == null) ? 0 : getSuspendTime().hashCode());
        result = prime * result + ((getSuspendOperator() == null) ? 0 : getSuspendOperator().hashCode());
        result = prime * result + ((getOriAlarmType() == null) ? 0 : getOriAlarmType().hashCode());
        result = prime * result + ((getOriAlarmLevel() == null) ? 0 : getOriAlarmLevel().hashCode());
        result = prime * result + ((getAlarmTitle() == null) ? 0 : getAlarmTitle().hashCode());
        result = prime * result + ((getNodeTypeId() == null) ? 0 : getNodeTypeId().hashCode());
        result = prime * result + ((getNodeTypeName() == null) ? 0 : getNodeTypeName().hashCode());
        result = prime * result + ((getNodeName() == null) ? 0 : getNodeName().hashCode());
        result = prime * result + ((getGenerateTime() == null) ? 0 : getGenerateTime().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getDispatchStatus() == null) ? 0 : getDispatchStatus().hashCode());
        return result;
    }
}
