package com.topinternacional.linx.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.service.SOAPService;

public abstract class BasicService {
	
	@Autowired
	protected ExecucoesRepository execRepo;
 	
	@Autowired
	protected SOAPService soap;
}
