package com.affiliatelink;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import com.affiliateLink.AffiliatelinkApplication;

public class ModularityTest {
    static ApplicationModules modules =
        ApplicationModules.of(AffiliatelinkApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.forEach(System.out::println);
        modules.verify();
    }
}