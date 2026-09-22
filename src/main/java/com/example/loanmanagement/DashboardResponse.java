package com.example.loanmanagement;

public class DashboardResponse {

    private Long totalapplications;
    private Long pendingloans;
    private Long approvedloans;
    private Long rejectedloans;
    private Long closedloans;
    private Double totalloanamount;

    public DashboardResponse(){

    }

    //total applications
    public void setTotalapplications(Long totalapplications){
        this.totalapplications = totalapplications;
    }
    public Long getTotalapplications(){
        return totalapplications;
    }

    //pending loans
    public void setPendingloans(Long pendingloans){
        this.pendingloans = pendingloans;
    }
    public Long getPendingloans(){
        return pendingloans;
    }

    //approved loans
    public void setApprovedloans(Long approvedloans){
        this.approvedloans = approvedloans;
    }
    public Long getApprovedloans(){
        return approvedloans;
    }

    //rejected loans
    public void setRejectedloans(Long rejectedloans){
        this.rejectedloans = rejectedloans;
    }
    public Long getRejectedloans() {
        return rejectedloans;
    }

    //closed loans
    public void setClosedloans(Long closedloans) {
        this.closedloans = closedloans;
    }
    public Long getClosedloans() {
        return closedloans;
    }

    //total loan amount
    public void setTotalloanamount(Double totalloanamount) {
        this.totalloanamount = totalloanamount;
    }
    public Double getTotalloanamount() {
        return totalloanamount;
    }
}
