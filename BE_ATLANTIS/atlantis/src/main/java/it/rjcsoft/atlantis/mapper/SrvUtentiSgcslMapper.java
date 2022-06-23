package it.rjcsoft.atlantis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import it.rjcsoft.atlantis.model.SrvUtentiSgcsl;

@Mapper
public interface SrvUtentiSgcslMapper {
	
	
	@Select("SELECT [LP_NUM] ,[LP_CODICE] ,[LP_PADRE],[LP_CATEGORIA],[LP_NOME],[LP_COGNOME],[LOGIN],[LP_PROFILO_OPER],[PSW],[IDPROFILE],[LP_GROUP_LAVORO],[NOTE],[ABILITATO],[USERID],[DATAINS],[DATAMOD] FROM [ATLANTIS_NEW].[dbo].[SRV_UTENTI_SGCSL]")
    List<SrvUtentiSgcsl> getAll();

	@Select("SELECT [LP_NUM] ,[LP_CODICE] ,[LP_PADRE],[LP_CATEGORIA],[LP_NOME],[LP_COGNOME],[LOGIN],[LP_PROFILO_OPER],[PSW],[IDPROFILE],[LP_GROUP_LAVORO],[NOTE],[ABILITATO],[USERID],[DATAINS],[DATAMOD] FROM [ATLANTIS_NEW].[dbo].[SRV_UTENTI_SGCSL] WHERE [LOGIN] = #{username}")
    SrvUtentiSgcsl login(@Param("username") String username);

	@Select("SELECT [LP_NUM] ,[LP_CODICE] ,[LP_PADRE],[LP_CATEGORIA],[LP_NOME],[LP_COGNOME],[LOGIN],[LP_PROFILO_OPER],[PSW],[IDPROFILE],[LP_GROUP_LAVORO],[NOTE],[ABILITATO],[USERID],[DATAINS],[DATAMOD] FROM [ATLANTIS_NEW].[dbo].[SRV_UTENTI_SGCSL] WHERE [LOGIN] = #{username} AND [PSW]=#{password}")
    SrvUtentiSgcsl getProfiloUtente(@Param("username") String username,@Param("password") String password);
}
