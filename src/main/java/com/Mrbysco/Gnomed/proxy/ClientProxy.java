package com.Mrbysco.Gnomed.proxy;

import com.Mrbysco.Gnomed.init.GnomeEntities;

public class ClientProxy extends ServerProxy{
	
	@Override
	public void Preinit(){
		GnomeEntities.RegisterEntityRender();
	}
	
	@Override
	public void Init() {
		
	}
}
