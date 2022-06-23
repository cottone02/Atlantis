package it.rjcsoft.atlantis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import it.rjcsoft.atlantis.model.SrvProfileValues;

@Mapper
public interface SrvProfileValuesMapper {
	@Select("select [ID_PROFILE],[ID_ELEMENT],[VALUE]  from srv_profile_values where id_profile=#{idProfile} and ID_ELEMENT like #{idElement} and value <> 'N'")
	List<SrvProfileValues> abilitazioneMenu(@Param("idProfile") Long idProfile, @Param("idElement") String idElement);
}
