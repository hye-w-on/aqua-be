package com.aqua.aquabe.service.common;

import com.aqua.aquabe.model.common.SampleRequestVO;
import com.aqua.aquabe.model.common.SampleResponseVO;

public interface RestTemplateService {

    SampleResponseVO getSample(SampleRequestVO sampleRequestVO);

}
