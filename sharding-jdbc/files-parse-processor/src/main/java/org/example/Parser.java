package org.example;

import java.io.File;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public interface Parser {

    /**
     * 解析文件的逻辑
     * @param file
     * @return
     * @throws Exception
     */
    String parser(File file) throws Exception;

    //文件类型
    String getType();
}
