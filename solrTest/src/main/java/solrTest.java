import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-solr.xml")
public class solrTest {
    @Autowired
    private SolrTemplate solrTemplate;
    @Test
    public  void testTemplate(){
        TbItem item = new TbItem();
        item.setBrand("apple");
        item.setId(1L);
        item.setPrice(new BigDecimal(666.25));
        item.setTitle("最新款22");
        solrTemplate.saveBean(item);
        solrTemplate.commit();
        SimpleQuery query = new SimpleQuery("*:*");
        Criteria c = new Criteria("item_title").contains("2");
        c=c.or("item_title").contains("5");

        query.addCriteria(c);

        TbItem tbItem = solrTemplate.queryForObject(query, TbItem.class);
        System.out.println(tbItem);
    }

}
