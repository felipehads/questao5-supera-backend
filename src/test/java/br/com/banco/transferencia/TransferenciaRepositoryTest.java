package br.com.banco.transferencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.banco.entity.ContaEntity;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.domain.dto.request.GetTransferenciaRequest;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TransferenciaRepositoryTest {

    @Autowired
    TransferenciaRepository repository;

    // A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária
    // Logo, se o filtro for vazio, retorna-se um array vazio, pois não está associado a nenhuma conta bancária
    @Test
    public void shouldReturnAnEmptyArray() {

        GetTransferenciaRequest filtro = new GetTransferenciaRequest();

        List<TransferenciaEntity> result = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result.size() == 0);
    }

    // Existem 3 transferências referente à idConta 1, e mais 3 referentes à idConta 2
    @Test
    public void shouldReturnTransferenciasFilteredByIdConta1() {

        // idConta = 1
        GetTransferenciaRequest filtro = new GetTransferenciaRequest();
        filtro.setIdConta(1);

        List<TransferenciaEntity> result1 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result1.size() == 3);
        Assertions.assertTrue(result1.get(0).getConta().getIdConta() == 1);
        Assertions.assertTrue(result1.get(1).getConta().getIdConta() == 1);
        Assertions.assertTrue(result1.get(2).getConta().getIdConta() == 1);
        
        
        // ---------- Filtrando por Data
        
        // Data Início maior que qualquer data de Transferência -> Retorna Array Vazio
        filtro.setDataInicio(LocalDate.of(2022, 1, 1));
        
        List<TransferenciaEntity> result2 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result2.size() == 0);
        
        // Data Início menor que qualquer data de Transferência -> Retorno com 3 Transferências
        filtro.setDataInicio(LocalDate.of(2018, 1, 1));
        
        List<TransferenciaEntity> result3 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result3.size() == 3);
        Assertions.assertTrue(result3.get(0).getConta().getIdConta() == 1);
        Assertions.assertTrue(result3.get(1).getConta().getIdConta() == 1);
        Assertions.assertTrue(result3.get(2).getConta().getIdConta() == 1);

        // Data Início e Data Fim filtrando as 2 primeiras Transferências -> Retorno com 2 Transferências
        filtro.setDataInicio(LocalDate.of(2018, 1, 1));
        filtro.setDataFim(LocalDate.of(2019, 12, 30));
        
        List<TransferenciaEntity> result4 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result4.size() == 2);
        Assertions.assertTrue(result4.get(0).getConta().getIdConta() == 1);
        Assertions.assertTrue(result4.get(1).getConta().getIdConta() == 1);

        // --------- Filtrando apenas por Nome do Operador

        // Filtrando com nome incorreto do operador -> Retorna um Array vazio
        filtro.setDataInicio(null);
        filtro.setDataFim(null);
        filtro.setOperadorTransacao("Teste");

        List<TransferenciaEntity> result5 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result5.size() == 0);

        // Filtrando com nome incorreto do operador -> Retorno com 1 Transferência
        filtro.setOperadorTransacao("Beltrano");

        List<TransferenciaEntity> result6 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result6.size() == 1);
        Assertions.assertTrue(result6.get(0).getConta().getIdConta() == 1);


        // Filtrando uma Transferência específica

        List<ContaEntity> contas = new ArrayList<>();
        contas.add(0, new ContaEntity(1,"Fulano"));

        List<TransferenciaEntity> transferencias = new ArrayList<>();
        transferencias.add(0, new TransferenciaEntity(1, LocalDateTime.of(2019,01,01,12,00,00), Double.valueOf(30895.46), "DEPOSITO", null, contas.get(0)));

        filtro.setDataInicio(LocalDate.of(2018,12,12));
        filtro.setDataFim(LocalDate.of(2019,2,2));
        filtro.setOperadorTransacao(null);


        List<TransferenciaEntity> result7 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result7.size()==1);
        Assertions.assertEquals(transferencias.get(0).getId(), result7.get(0).getId());
        Assertions.assertEquals(transferencias.get(0).getValor(), result7.get(0).getValor());
        Assertions.assertEquals(transferencias.get(0).getNomeOperadorTransacao(), result7.get(0).getNomeOperadorTransacao());
        Assertions.assertEquals(transferencias.get(0).getTipo(), result7.get(0).getTipo());
        Assertions.assertEquals(transferencias.get(0).getDataTransferencia(), result7.get(0).getDataTransferencia());
        Assertions.assertEquals(transferencias.get(0).getConta().getIdConta(), result7.get(0).getConta().getIdConta());
        Assertions.assertEquals(transferencias.get(0).getConta().getNomeResponsavel(), result7.get(0).getConta().getNomeResponsavel());

    }

    @Test
    public void shouldReturnTransferenciasFilteredByIdConta2() {

        // idConta = 2
        GetTransferenciaRequest filtro = new GetTransferenciaRequest();
        filtro.setIdConta(2);

        List<TransferenciaEntity> result = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result.size() == 3);
        Assertions.assertTrue(result.get(0).getConta().getIdConta() == 2);
        Assertions.assertTrue(result.get(1).getConta().getIdConta() == 2);
        Assertions.assertTrue(result.get(2).getConta().getIdConta() == 2);


        // ---------- Filtrando por Data
        
        // Data Início maior que qualquer data de Transferência -> Retorna Array Vazio
        filtro.setDataInicio(LocalDate.of(2022, 1, 1));
        
        List<TransferenciaEntity> result2 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result2.size() == 0);
        
        // Data Início menor que qualquer data de Transferência -> Retorno com 3 Transferências
        filtro.setDataInicio(LocalDate.of(2018, 1, 1));
        
        List<TransferenciaEntity> result3 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result3.size() == 3);
        Assertions.assertTrue(result3.get(0).getConta().getIdConta() == 2);
        Assertions.assertTrue(result3.get(1).getConta().getIdConta() == 2);
        Assertions.assertTrue(result3.get(2).getConta().getIdConta() == 2);

        // Data Início e Data Fim filtrando as 2 primeiras Transferências -> Retorno com 2 Transferências
        filtro.setDataInicio(LocalDate.of(2018, 1, 1));
        filtro.setDataFim(LocalDate.of(2019, 12, 30));
        
        List<TransferenciaEntity> result4 = repository.findAllByFiltro(filtro);
        
        Assertions.assertTrue(result4.size() == 2);
        Assertions.assertTrue(result4.get(0).getConta().getIdConta() == 2);
        Assertions.assertTrue(result4.get(1).getConta().getIdConta() == 2);

        // --------- Filtrando apenas por Nome do Operador

        // Filtrando com nome incorreto do operador -> Retorna um Array vazio
        filtro.setDataInicio(null);
        filtro.setDataFim(null);
        filtro.setOperadorTransacao("Teste");

        List<TransferenciaEntity> result5 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result5.size() == 0);

        // Filtrando com nome incorreto do operador -> Retorno com 1 Transferência
        filtro.setOperadorTransacao("Ronnyscley");

        List<TransferenciaEntity> result6 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result6.size() == 1);
        Assertions.assertTrue(result6.get(0).getConta().getIdConta() == 2);


        // Filtrando uma Transferência específica

        List<ContaEntity> contas = new ArrayList<>();
        contas.add(0, new ContaEntity(2,"Sicrano"));

        List<TransferenciaEntity> transferencias = new ArrayList<>();
        transferencias.add(0, new TransferenciaEntity(2, LocalDateTime.of(2019,02,03,9,53,27), Double.valueOf(12.24), "DEPOSITO", null, contas.get(0)));


        filtro.setDataInicio(LocalDate.of(2018,12,12));
        filtro.setDataFim(LocalDate.of(2019,6,6));
        filtro.setOperadorTransacao(null);


        List<TransferenciaEntity> result7 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result7.size()==1);
        Assertions.assertEquals(transferencias.get(0).getId(), result7.get(0).getId());
        Assertions.assertEquals(transferencias.get(0).getValor(), result7.get(0).getValor());
        Assertions.assertEquals(transferencias.get(0).getNomeOperadorTransacao(), result7.get(0).getNomeOperadorTransacao());
        Assertions.assertEquals(transferencias.get(0).getTipo(), result7.get(0).getTipo());
        Assertions.assertEquals(transferencias.get(0).getDataTransferencia(), result7.get(0).getDataTransferencia());
        Assertions.assertEquals(transferencias.get(0).getConta().getIdConta(), result7.get(0).getConta().getIdConta());
        Assertions.assertEquals(transferencias.get(0).getConta().getNomeResponsavel(), result7.get(0).getConta().getNomeResponsavel());
    }

    // idConta = 3 (ou qualquer outro valor diferente de 1 e 2)
    @Test
    public void shouldReturnTransferenciasFilteredByIdConta3OrOtherNumber() {

        GetTransferenciaRequest filtro = new GetTransferenciaRequest();
        filtro.setIdConta(3);

        List<TransferenciaEntity> result = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result.size() == 0);

        filtro.setIdConta(999);

        List<TransferenciaEntity> result2 = repository.findAllByFiltro(filtro);

        Assertions.assertTrue(result2.size() == 0);
        
    }
    
}
