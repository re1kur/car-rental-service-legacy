package service;


import dao.CompanyDao;
import dto.read.ReadCompanyDto;
import dto.write.CreateCompanyDto;
import entity.CompanyEntity;
import lombok.Getter;
import lombok.SneakyThrows;
import util.mapper.CreateCompanyMapper;
import util.mapper.ReadCompanyMapper;

import java.util.List;

public class CompanyService {
    @Getter
    private static final CompanyService instance = new CompanyService();
    private final CompanyDao companyDao = CompanyDao.getInstance();
    private final ReadCompanyMapper readMapper = ReadCompanyMapper.getInstance();
    private final CreateCompanyMapper createMapper = CreateCompanyMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private CompanyService() {
    }

    public List<ReadCompanyDto> readAll() {
        return companyDao.findAll().stream().map(
                readMapper::map).toList();
    }

    @SneakyThrows
    public void create(CreateCompanyDto companyDto) {
        CompanyEntity mapped = createMapper.map(companyDto);
        imageService.upload(mapped.getImgKey(), companyDto.getImage().getInputStream());
        companyDao.save(mapped);
    }
}
