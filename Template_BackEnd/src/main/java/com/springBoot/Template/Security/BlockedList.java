package com.springBoot.Template.Security;

import com.springBoot.Template.Model.LogOut;
import com.springBoot.Template.Repository.LogoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// BlockedList Token Class
@Service
public class BlockedList {

    // Import Other Class Properties
    @Autowired
    private LogoutRepository repository;
    @Autowired
    private JwtUtils jwtUtils;

    // Logging Configuration For This Class
    private static final Logger log = LoggerFactory.getLogger(BlockedList.class);

    // Checked LogOut Token Validation Delete Automatically
    public void checked () {
        List<LogOut> data = repository.findAll();
        jwtUtils.init();
        log.info(data.toString());
        for (LogOut datum : data) {
            String temp = datum.getToken();
            if(temp!=null) {
                if (jwtUtils.block(temp.substring(7))) {
                    repository.deleteById(datum.getId());
                    log.info(temp);
                }
            }
        }
    }

}

