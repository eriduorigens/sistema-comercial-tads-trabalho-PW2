package br.edu.ifg.sistemacomercial.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import br.edu.ifg.sistemacomercial.entity.Categoria;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria> {

    @Override
    public Categoria getAsObject(FacesContext context, UIComponent uic, String id) {
        
        
        if (id != null && !"".equals(id)) {
            Categoria categoria =(Categoria) uic.getAttributes().get("categoria_" +id);
            return categoria;
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent unic, Categoria value) {
        if (value != null && value.getId() != null) {

            unic.getAttributes().put("categoria_" + value.getId(), value);
            return value.getId().toString();
        }
        return "";
    }

}
