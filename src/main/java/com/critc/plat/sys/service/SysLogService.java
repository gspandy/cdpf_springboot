package com.critc.plat.sys.service;

import com.critc.plat.sys.dao.ISysLogDao;
import com.critc.plat.sys.model.SysLog;
import com.critc.plat.sys.vo.SysLogSearchVO;
import com.critc.plat.util.date.DateUtil;
import com.critc.plat.util.excel.CSVUtil;
import com.critc.plat.util.file.DownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * 系统日志service
 *
 * @author chykong
 */
@Service
public class SysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 操作时记录日志
     */
    @Async
    public void addLog(int userId, String url, String parameters, String operaIp) {
        /*HashMap<String, SysFunction> hashMap = sysFunctionService.getAllFunction();//获取所有function

        SysLog sysLog = new SysLog();
        sysLog.setUser_id(user_id);
        sysLog.setOpera_url(url);
        if (parameters.length() > 500)
            parameters = parameters.substring(0, 500);
        sysLog.setOpera_params(parameters);
        sysLog.setOpera_date(new Date());
        sysLog.setOpera_ip(opera_ip);

        SysFunction sysFunction = hashMap.get(url);
        if (sysFunction != null) {
            sysLog.setModule_name(sysFunction.getModule_name());
            sysLog.setOpera_name(sysFunction.getName());
            sysLog.setOpera_type(sysFunction.getType());
        }
        sysLogDao.addTx(sysLog);*/
    }

    /**
     * 日志列表
     *
     * @param sysLogSearchVO
     * @return
     */
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        List<SysLog> list = sysLogDao.list(sysLogSearchVO);
        return list;
    }

    /**
     * 日志列表
     *
     * @param sysLogSearchVO
     * @return
     */
    public List<SysLog> listAll(SysLogSearchVO sysLogSearchVO) {
        List<SysLog> list = sysLogDao.listAll(sysLogSearchVO);
        return list;
    }

    /**
     * 日志列表总数
     *
     * @param sysLogSearchVO
     * @return
     */
    public int listCount(SysLogSearchVO sysLogSearchVO) {
        return sysLogDao.listCount(sysLogSearchVO);
    }

    /**
     * 下载日志
     *
     * @param sysLogSearchVO
     */
    public void exportLog(String path, SysLogSearchVO sysLogSearchVO, HttpServletRequest request, HttpServletResponse response) {
        List<SysLog> list = sysLogDao.list(sysLogSearchVO);
        String[][] data = new String[list.size() + 1][9];
        data[0][0] = "序号";
        data[0][1] = "用户编号";
        data[0][2] = "用户姓名";
        data[0][3] = "操作时间";
        data[0][4] = "IP";
        data[0][5] = "模块名称";
        data[0][6] = "操作名称";
        data[0][7] = "操作url";
        data[0][8] = "参数";

        for (int i = 0; i < list.size(); i++) {
            SysLog sysLog = list.get(i);
            data[i + 1][0] = String.valueOf(i + 1);
            data[i + 1][1] = String.valueOf(sysLog.getUserId());
            data[i + 1][2] = sysLog.getRealname();
            data[i + 1][3] = DateUtil.dateToString(sysLog.getOperaDate(), "yyyy-MM-dd HH:mm:ss");
            data[i + 1][4] = sysLog.getOperaIp();
            data[i + 1][5] = sysLog.getModuleName();
            data[i + 1][6] = sysLog.getOperaName();
            data[i + 1][7] = sysLog.getOperaUrl();
            data[i + 1][8] = sysLog.getOperaParams();
        }

        String fileName = "opereaLog";
        File file = CSVUtil.createCSVFile(path, fileName, data);
        DownloadUtil.download(response, path + "/" + file.getName(), fileName + ".CSV");
    }
}
