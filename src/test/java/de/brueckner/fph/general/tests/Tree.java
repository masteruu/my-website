package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


public class Tree extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(Tree.class);

    @Test
    public void treeTest() {

        //open tree
        treeManager.openTree();

        logger.info("Tree opened successful");
    }
}
