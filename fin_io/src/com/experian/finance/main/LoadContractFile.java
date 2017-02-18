/**
 * 
 */
package com.experian.finance.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.experian.finance.dto.InsertData;
import com.experian.finance.master.Contract;

/**
 * @author C02809A
 * 
 */
public class LoadContractFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length ==0){
			System.err.println("Invalid Number of Arguments");
			System.exit(0);
		}
		
		File contractFile = new File(args[0]);
		List<String> contractList = new ArrayList<String>();
		List<Contract> contractColl = new ArrayList<Contract>();
		InsertData insertData = new InsertData();
		Contract contract;
		System.out.println(contractFile + " File Exist");

		File fileNameObj1 = new File(contractFile.getName());
		

		try {
			FileReader fileReader1 = new FileReader(contractFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader1);
			String readerString = bufferedReader.readLine();

			while (null != readerString) {
				contractList.add(readerString.toUpperCase());
				readerString = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int a = 1; a < contractList.size(); a++) {
			
			if (contractList.get(a)== null || contractList.get(a).length() == 0)
				continue;
			
			String[] splitArr = StringUtils.splitPreserveAllTokens(contractList.get(a),',');
			
			if (splitArr.length < 56)
				continue;
			
			contract = new Contract(splitArr);
			contract.populateNullFields();
			contractColl.add(contract);
			try {
				insertData.insertContract(contract);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("fileNameObj " + fileNameObj1);
	}

}
