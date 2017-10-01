package net.de1mos.test.testjava9spring5.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@RestController
@RequestMapping("/rest")
public class SimpleEndpoint {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam("id") String id) {
        String
                name =
                jdbcTemplate.queryForObject("SELECT firstname FROM person WHERE id = ?",
                        new Object[]{Long.valueOf(id)},
                        String.class);
        return "Hello dear " + name;
    }

    @GetMapping("save")
    public String savePerson(@RequestParam("name") String name, @RequestParam("last") String last) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
                    PreparedStatement ps =
                            con.prepareStatement("INSERT INTO person (firstname, lastname, state) VALUES (?,?,?)",
                                    new String[]{"id"});
                    ps.setString(1, name);
                    ps.setString(2, last);
                    ps.setString(3, "RU");
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().toString();
    }
}
