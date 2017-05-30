package top.auok.cbps.ts.user.entity;

import java.io.Serializable;

import top.auok.cbps.ts.core.entity.BaseEntity;
import top.auok.cbps.ts.core.enums.PublicEnum;

/**
 * 支付产品实体类
 */
public class RpPayProduct extends BaseEntity implements Serializable {

    private String productCode;

    private String productName;
    
    private String auditStatus;

    private static final long serialVersionUID = 1L;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }
    
    public String getAuditStatusDesc() {
    	return PublicEnum.getEnum(this.getAuditStatus()).getDesc();
    }
}