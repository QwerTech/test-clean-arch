package org.example.dataproviders.csv.impl;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.example.dataproviders.csv.OrdersCsvBuilder;
import org.example.entities.Order;
import org.springframework.stereotype.Component;

@Component
class OrdersCsvBuilderImpl implements OrdersCsvBuilder {

  @Override
  @SneakyThrows
  public byte[] buildOrdersCsv(Stream<Order> records) {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    Writer writer = new OutputStreamWriter(output, StandardCharsets.UTF_8);

    StatefulBeanToCsv<OrderCsvRecord> sbc = new StatefulBeanToCsvBuilder<OrderCsvRecord>(writer)
        .build();

    sbc.write(records.map(OrderCsvRecord::from));
    writer.close();
    return output.toByteArray();
  }
}
