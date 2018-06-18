package util;

import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 分页实体
 */
public class PageModel implements Serializable {

    //分页总数据条数
    private int recordCount;
    //当前页面
    private int pageIndex;
    //每页分多少条数据
    private int pageSize = HrmConstants.PAGE_DEFAULT_SIZE  = 4;
    //总页数
    private int totalSize;


    public int getRecordCount(){
        this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
        //判断当前页面是否超过总页数
        //如果超过默认将最后一页当作当前页
        this.pageIndex = this.pageIndex >= this.getTotalSize()?this.getTotalSize():this.getTotalSize();
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        this.pageSize = this.pageSize <= HrmConstants.PAGE_DEFAULT_SIZE ? HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        if (this.getRecordCount() <= 0){
            totalSize = 0;
        }else {
            totalSize = (this.getRecordCount() - 1) / this.getPageSize() + 1;
        }
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getFirstLimitParam(){
        return (this.getPageIndex() - 1) * this.getPageSize();
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "recordCount=" + recordCount +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                '}';
    }
}
