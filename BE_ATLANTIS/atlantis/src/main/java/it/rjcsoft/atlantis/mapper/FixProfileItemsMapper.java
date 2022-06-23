package it.rjcsoft.atlantis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import it.rjcsoft.atlantis.model.FixProfileItems;

@Mapper
public interface  FixProfileItemsMapper {
	
	@Select("SELECT [ID_NUM],[NOME],[DESCRIZIONE],[POSITION],[URL],[ACTIVE],[ICON],[TITLE],[LABEL] FROM [ATLANTIS_NEW].[dbo].[FIX_PROFILE_ITEMS]  where nome !='GRP_GEST_DPI' AND active!='N' AND nome like '%' and nome not like '%.%' order by position")
	List<FixProfileItems> menuPrimoLivello();

	@Select("SELECT [ID_NUM],[NOME],[DESCRIZIONE],[POSITION],[URL],[ACTIVE],[ICON],[TITLE],[LABEL] FROM [ATLANTIS_NEW].[dbo].[FIX_PROFILE_ITEMS]  where  active!='N' AND nome like #{nome}  and nome not like #{nome2} order by position")
	List<FixProfileItems> menuSecondoLivello( @Param("nome") String nome,@Param("nome2") String nome2);
}
