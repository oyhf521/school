package cn.afterturn.easypoi.handler.inter;

import java.util.List;

/**
 * 导出数据接口
 * @author admin
 * 2016年9月8日
 */
public interface IExcelExportServer {

    public List<Object> selectListForExcelExport(Object obj, int page);

}
