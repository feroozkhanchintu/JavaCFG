package com.codenation.java.pdg;

import com.codenation.java.pdg.decomposition.cfg.CFG;
import com.codenation.java.pdg.util.FileUtil;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.codenation.java.pdg.ASTReader.createCompilationUnit;

/**
 * Created by Ferooz on 13/02/17.
 */
public class Run {

    public static void main(String[] args) throws IOException {

        String folderPath = "/Users/Ferooz/Git_Src/jOOQ";
//        String folderPath = "/Users/Ferooz/Downloads/crawler4j-master";

        List<File> javaFiles = FileUtil.getAllFilesInDirectory(folderPath, new String[]{"java"});
        System.out.println(javaFiles.size());

        ASTReader astReader = new ASTReader();
        int i = 1;

        for (File file2 : javaFiles) {
//            File file2 = new File("/Users/Ferooz/Downloads/crawler4j-master/src/main/java/edu/uci/ics/crawler4j/crawler/Page.java");
            CompilationUnit compilationUnit = createCompilationUnit(file2.getAbsolutePath(), new String[]{folderPath}, null);
            CompilationUnitCache.compilationUnitList.add(compilationUnit);
            ASTInformationGenerator.setCurrentCompilationUnit(compilationUnit);
            List<ClassObject> classObjects = astReader.parseAST(compilationUnit);

            System.out.println(i++ + "   " + file2.getAbsolutePath());
            for (ClassObject classObject : classObjects) {
                for (MethodObject methodObject : classObject.getMethodList()) {
                    CFG cfg = new CFG(methodObject);
//                    System.out.println(cfg.getEdges());
                }
            }

        }
    }
}
