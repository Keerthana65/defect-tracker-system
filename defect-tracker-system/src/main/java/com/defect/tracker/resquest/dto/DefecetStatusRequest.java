package com.defect.tracker.resquest.dto;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service

public class DefecetStatusRequest {
    private Long id;
    private String name;
}
