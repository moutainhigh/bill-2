package com.herongtech.console.service.common.audit.util;

import com.herongtech.fileresolver.impl.AbstractFileProcessor;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.vfs.FileObject;
import com.herongtech.xstream.XStreamFactory;
import com.thoughtworks.xstream.XStream;


public class AuditTriggerXmlCfgProcessor extends AbstractFileProcessor {
    private static Logger logger = LoggerFactory.getLogger(AuditTriggerXmlCfgProcessor.class);
    
    private static final String TRIGGER_EXT_FILENAME = ".auditcallback.xml";

    @Override
    public void process() {
        process(this.getClass().getClassLoader());
    }

    public void process(ClassLoader loader) {
        XStream stream = XStreamFactory.getXStream("audit.trigger");
        for (FileObject fileObject : fileObjects) {
            logger.infoMessage("common.audit.trigger.readmapping.start", fileObject.getAbsolutePath());
            AuditTriggers mappings = (AuditTriggers) stream.fromXML(fileObject.getInputStream());
            AuditTriggerUtil.addTriggerMapping(mappings);
            logger.infoMessage("common.audit.trigger.readmapping.end", fileObject.getAbsolutePath());
        }
    }

    @Override
    protected boolean checkMatch(FileObject fileobject) {
     // 找到附合标准的文件
        return fileobject.getFileName().endsWith(TRIGGER_EXT_FILENAME);
    }

}
