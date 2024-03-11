package com.cibertec.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibertec.model.ciudad;
import com.cibertec.model.venta;
import com.cibertec.model.detalle_venta;
import com.cibertec.repository.ICiudadRepository;
import com.cibertec.repository.IDetalleRepository;
import com.cibertec.repository.IVentaRepository;

public class ventaController {

	@Autowired
	private ICiudadRepository ciudadRepository;
	
	@Autowired 
	private IVentaRepository ventaRepository;
	
	@Autowired
	private IDetalleRepository detalleRepository;
	
    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index";
    }
	
	@GetMapping("/index")
	public String listaCiudades(Model model) {
		List<ciudad> lista_ciudades = ciudadRepository.findAll();
		model.addAttribute("lista_ciudades",lista_ciudades);
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregarVenta(
			@RequestParam("origen") String origen ,
			@RequestParam("destino") String destino,
            @RequestParam("fecha_salida") String fechaSalida,
            @RequestParam("fecha_retorno") String fechaRetorno,
            @RequestParam("comprador") String nombreComprador,
            @RequestParam("cantidad") int cantidad, Model model, HttpSession session) {

		List<ciudad> lista_ciudades = ciudadRepository.findAll();
		
        

		//Obtener las ciudades de la base de datos
		ciudad ciudad_origen = ciudadRepository.findById(origen).orElse(null);
		ciudad ciudad_destino = ciudadRepository.findById(destino).orElse(null);
		
		LocalDate fecha_salida = LocalDate.parse(fechaSalida);
		LocalDate fecha_retorno = LocalDate.parse(fechaRetorno);

        @SuppressWarnings("unchecked")
		List<detalle_venta> lista_detalle = (List<detalle_venta>) session.getAttribute("lista_detalle");
        if (lista_detalle == null) {
            lista_detalle = new ArrayList<>();
        }
		Date fecha_Salida =  Date.valueOf(fecha_salida);
		Date fecha_Retorno =  Date.valueOf(fecha_retorno);
		
		double precioBoleto = 50;
		
		detalle_venta nuevoDetalle = new detalle_venta();
		nuevoDetalle.setCodigoOrigen(ciudad_origen);
		nuevoDetalle.setCodigoDestino(ciudad_destino);
		nuevoDetalle.setFechaViaje(fecha_Salida);
		nuevoDetalle.setFechaRetorno(fecha_Retorno);
		nuevoDetalle.setSubTotal(precioBoleto*cantidad);
		
		lista_detalle.add(nuevoDetalle);
		
		Double monto_total = 0.0;
		for(detalle_venta detalle : lista_detalle) {
			monto_total +=detalle.getSubTotal();
		}
		
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		
		venta vent = new venta();
		vent.setNombreComprador(nombreComprador);
		vent.setFechaVenta(date);
		vent.setMontoTotal(monto_total);
		
		lista_detalle.forEach(detalle -> detalle.setIdVenta(vent));
		
		session.setAttribute("venta", vent);
		session.setAttribute("lista_detalle", lista_detalle);
		model.addAttribute("lista_detalle", lista_detalle);
		model.addAttribute("lista_ciudades", lista_ciudades);
		//model.addAttribute("ciudad_origen",ciudad_origen);
		//model.addAttribute("ciudad_destino",ciudad_destino);
		
		return "index";
	}
	
	@GetMapping("/comprar")
	public String comprarBoleto(Model model, HttpSession session) {
	    @SuppressWarnings("unchecked")
		List<detalle_venta> lista_detalles = (List<detalle_venta>) session.getAttribute("lista_detalles");
	    venta vent = (venta) session.getAttribute("venta");
	    
	    if(lista_detalles != null && !lista_detalles.isEmpty()) {
	        ventaRepository.save(vent);
	        
	        lista_detalles.forEach(e -> {
	            e.setIdVenta(vent);
	            detalleRepository.save(e);
	        });
	        
	        lista_detalles = new ArrayList<>();
	        venta venta_nueva = new venta();
	        
	        session.setAttribute("venta", venta_nueva);
	        session.setAttribute("lista_detalles", lista_detalles);
	        return "mensaje";
	    }
	    return "redirect:/index";
	}
	
}
