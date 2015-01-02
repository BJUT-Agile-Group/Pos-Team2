package test;

import Utility.ManageDao;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by KEN on 2015/1/2.
 */
public class ManageDaoTest  extends TestCase{

    public static Test suite(){
        return new TestSuite(ManageDao.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
