package com.deyuan.gmall.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.deyuan.gmall.bean.PmsSearchSkuInfo;
import com.deyuan.gmall.bean.PmsSkuInfo;
import com.deyuan.gmall.service.PmsSkuService;
import io.reactivex.functions.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallSearchServiceApplicationTests {

    @Reference
    PmsSkuService pmsSkuService;

    @Autowired
    JestClient jestClient;
    @Test
    public void contextLoads() throws IOException {

        List<PmsSkuInfo> pmsSkuInfoList =new ArrayList<>();
        pmsSkuInfoList=pmsSkuService.getAllSku();

        List<PmsSearchSkuInfo> pmsSearchSkuInfos=new ArrayList<>();

        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
            PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo();
            BeanUtils.copyProperties(pmsSkuInfo,pmsSearchSkuInfo);
            pmsSearchSkuInfos.add(pmsSearchSkuInfo);
        }
        for (PmsSearchSkuInfo searchSkuInfo : pmsSearchSkuInfos) {
            Index put=new Index.Builder(searchSkuInfo).index("gmall").type("skuInfo").id(searchSkuInfo.getId()+"").build();
            jestClient.execute(put);
        }


    }
    @Test
    public void qurey() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //filter
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("skuAttrValueList.valueId","74");
        boolQueryBuilder.filter(termQueryBuilder);
        //must
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName","小米");
       boolQueryBuilder.must(matchQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        String dslStr=searchSourceBuilder.toString();
        List<PmsSearchSkuInfo> searchSkuInfos=new ArrayList<>();
        Search search = new Search.Builder(dslStr).addIndex("gmall").addType("skuInfo").build();
        SearchResult execute = jestClient.execute(search);
        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hits = execute.getHits(PmsSearchSkuInfo.class);
        for (SearchResult.Hit<PmsSearchSkuInfo, Void> hit : hits) {
            PmsSearchSkuInfo source = hit.source;
            searchSkuInfos.add(source);
        }
        System.out.println(searchSkuInfos);


    }

}
