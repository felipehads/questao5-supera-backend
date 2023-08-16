package br.com.banco.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapperService {

    private final ModelMapper modelMapper;

    public <S, T> List<T> mapList(List<S> sourceList, Class<T> destinationClass) {
        return sourceList
                .stream()
                .map(source -> modelMapper.map(source, destinationClass))
                .collect(Collectors.toList());
    }

    public <S, T> Page<T> mapPage(Page<S> sourceList, Class<T> destinationClass) {
        List<T> mappedList = sourceList
                .getContent()
                .stream()
                .map(source -> modelMapper.map(source, destinationClass))
                .collect(Collectors.toList());
        return new PageImpl<>(mappedList, sourceList.getPageable(), sourceList.getTotalElements());
    }
}
