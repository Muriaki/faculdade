package br.com.reserva.aerea.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.reserva.aerea.dao.AeroportoDAO;
import br.com.reserva.aerea.entity.Aeroporto;

@ManagedBean(name = "aeroportoBean")
@RequestScoped
public class AeroportoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jsonAeroportos;
	private List<Aeroporto> listaAeroportos;

	@EJB
	private AeroportoDAO aeroportoDAO;

	@PostConstruct
	public void inicializar() {
		listaAeroportos = aeroportoDAO.findAll();
		if(listaAeroportos != null && !listaAeroportos.isEmpty()){
			StringBuilder json = new StringBuilder();
			json.append("[");
			for (Aeroporto a : listaAeroportos) {
				json.append(a.toString()).append(",");
			}
			json.append("]");
			this.jsonAeroportos = json.toString();
		}
		System.out.println(jsonAeroportos);
	}

	public String getJsonAeroportos() {
		return jsonAeroportos;
	}
	
	public List<Aeroporto> getAllAeroportos(){
		if(listaAeroportos == null || listaAeroportos.isEmpty()){
			listaAeroportos = aeroportoDAO.findAll();
		}
		return this.listaAeroportos;
	}
	
	
	public void setJsonAeroportos(String jsonAeroportos) {
		this.jsonAeroportos = jsonAeroportos;
	}

}
