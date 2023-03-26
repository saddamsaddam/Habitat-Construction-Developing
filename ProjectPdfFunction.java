 int flatnumber=Database.getsharemateprojectflatnumber(pn);
                Document doc = new Document(PageSize.A4);
                OutputStream outputStream;
                outputStream = new FileOutputStream(new File("E:\\pdf\\habitat\\" + pn+".pdf"));
                PdfWriter writer= PdfWriter.getInstance(doc, outputStream);
                doc.setMargins(5, 1,10, 20);
               
                doc.open();
                String header1 = "Bismillah Habitat Company Limited ,Tangail, Bangladesh ";                
                paragraph = new Paragraph(header1, new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 11,Font.BOLD));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                doc.add(paragraph);
                paragraph = new Paragraph("Project Account", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                doc.add(paragraph);
                paragraph = new Paragraph(""+new Date()+"", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                paragraph.setSpacingAfter(20f);
                doc.add(paragraph);
                doc.add(new flatpdffunction("","").paragraphreturn("            Project Name: "+pn, "left", "bold"));
                for(int j=0;j<flatnumber;j++)
                {
                creditsum=0;debitsum=0;
                jsonObject= Database.getflatdebitcredit(pn,"Flat"+(j+1));
                paragraph = new Paragraph("              Flat name: Flat"+(j+1), new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                paragraph.setAlignment(Element.ALIGN_LEFT);
                paragraph.setSpacingAfter(10f);
                doc.add(paragraph); 
                 table = new PdfPTable(2);
                table.getDefaultCell().setBorderWidth(0f);
                cell1=new PdfPCell();
                cell1.addElement(paragraphreturn("Credit:", "center","bold"));
                cell1.setBorderWidth(0);
                table.addCell(cell1);
                cell1=new PdfPCell();
                cell1.addElement(paragraphreturn("Debit:", "center","bold"));
                cell1.setBorderWidth(0);
                table.addCell(cell1);
                table.setSpacingAfter(10f);
                doc.add(table);
                table = new PdfPTable(2);
                // table.getDefaultCell().setBorderWidth(0f)
                 table1 = new PdfPTable(2);
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Name", "center","bold"));
                 table1.addCell(cell1);
                 
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Money", "center","bold"));
                 cell1.disableBorderSide(8);
                 table1.addCell(cell1);
                 
                 
                   array=new JSONArray(jsonObject.getString("credit"));
                  for(int i=0;i<=array.length();i++)
                 {
                     if(i==array.length())
                     {
                     cell1=new PdfPCell();
                    cell1.addElement(new Paragraph("Total"));
                    table1.addCell(cell1);
                    
                     cell1=new PdfPCell();
                     
                     paragraph = new Paragraph(""+creditsum+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell1.addElement(paragraph);
                    cell1.disableBorderSide(8);
                    table1.addCell(cell1);
                     }
                     else
                     {
                     jsondata=new JSONObject(array.getString(i));
                     arrayname=new JSONArray(jsondata.getString("name"));
                    // System.out.print(arrayname.getString(0)+"\n");
                     arraymoney=new JSONArray(jsondata.getString("money"));
                     //System.out.print(arraymoney.getString(0)+"\n");
                     creditsum=creditsum+Integer.parseInt(arraymoney.getString(0));
                     
                    cell1=new PdfPCell();
                    cell1.addElement(new Paragraph(arrayname.getString(0)));
                    table1.addCell(cell1);
                    
                     cell1=new PdfPCell();
                     
                     paragraph = new Paragraph(""+arraymoney.getString(0)+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell1.addElement(paragraph);
                    cell1.disableBorderSide(8);
                    table1.addCell(cell1);
                     }
                 }
                  table.addCell(table1);
                //table1 end
                 table2 = new PdfPTable(3);
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Name", "center","bold"));
                 cell1.disableBorderSide(4);
                 table2.addCell(cell1);
                 
                 cell1=new PdfPCell();
                cell1.addElement(paragraphreturn("Amount", "center","bold"));
                 table2.addCell(cell1);
                 
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Money", "center","bold"));
                 table2.addCell(cell1);
                 
                   array=new JSONArray(jsonObject.getString("debit"));
                  for(int i=0;i<=array.length();i++)
                 {
                     if(i==array.length())
                     {
                     cell1=new PdfPCell();
                    cell1.addElement(new Paragraph("Total"));
                    cell1.disableBorderSide(4);
                    table2.addCell(cell1);
                    
                     cell1=new PdfPCell();
                     paragraph = new Paragraph();
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                     cell1.addElement(paragraph);
                     table2.addCell(cell1);
                    
                    cell1=new PdfPCell();
                     paragraph = new Paragraph(""+ debitsum+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                     cell1.addElement(paragraph);
                    table2.addCell(cell1);
                     }
                     else
                     {
                     jsondata=new JSONObject(array.getString(i));
                     arrayname=new JSONArray(jsondata.getString("name"));
                     arrayamount=new JSONArray(jsondata.getString("amount"));
                    // System.out.print(arrayname.getString(0)+"\n");
                     arraymoney=new JSONArray(jsondata.getString("money"));
                     //System.out.print(arraymoney.getString(0)+"\n");
                    debitsum=debitsum+Integer.parseInt(arraymoney.getString(0));
                     
                    cell1=new PdfPCell();
                    cell1.addElement(new Paragraph(arrayname.getString(0)));
                    cell1.disableBorderSide(4);
                    table2.addCell(cell1);
                    
                     cell1=new PdfPCell();
                     paragraph = new Paragraph(""+arrayamount.getString(0)+"", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                     cell1.addElement(paragraph);
                    table2.addCell(cell1);
                    
                    cell1=new PdfPCell();
                     paragraph = new Paragraph(""+arraymoney.getString(0)+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                     cell1.addElement(paragraph);
                    table2.addCell(cell1);
                     }  
                 }
                 table.addCell(table2);
                 table.setSpacingAfter(30f);
                 doc.add(table); 
                 
                 int sum=creditsum-debitsum;
                 if(sum>=0)
                 {
                   doc.add(paragraphreturn("Profit is:  "+sum+"/=", "center","bold"));
                 }
                 else
                 {
                 doc.add(paragraphreturn("Loss is:  "+sum+"/=", "center","bold"));  
                 }
                 
                 //total calculation
                  projectincome=projectincome+creditsum;
                  projectdebit=projectdebit+debitsum;
                  
                }
                paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_CENTER);
                paragraph.setSpacingAfter(30f);
                doc.add(paragraph);
                
                
                 int sum=projectincome-projectdebit;
                  if(sum>=0)
                 {
                   doc.add(paragraphreturn("All Flat Income:  "+projectincome+"/=", "center","bold"));
                   doc.add(paragraphreturn("All Flat Debit:  "+ projectdebit+"/=", "center","bold"));
                   doc.add(paragraphreturn("Profit is:  "+sum+"/=", "center","bold"));
                 }
                 else
                 {
                   doc.add(paragraphreturn("All Flat Income:  "+projectincome+"/=", "center","bold"));
                   doc.add(paragraphreturn("All Flat Debit:  "+ projectdebit+"/=", "center","bold"));
                 doc.add(paragraphreturn("Loss is:  "+sum+"/=", "center","bold"));  
                 }
                 
                  //capital table 
                  creditsum=0;debitsum=0;
                jsonObject= Database.projectcapital(pn);
                doc.add(new flatpdffunction("","").paragraphreturn("            Project Name: "+pn, "left", "bold"));
                table = new PdfPTable(2);
                table.getDefaultCell().setBorderWidth(0f);
                cell1=new PdfPCell();
                cell1.addElement(paragraphreturn("Capital:", "center","bold"));
                cell1.setBorderWidth(0);
                table.addCell(cell1);
                cell1=new PdfPCell();
                cell1.addElement(paragraphreturn("WithDraw:", "center","bold"));
                cell1.setBorderWidth(0);
                table.addCell(cell1);
                table.setSpacingAfter(10f);
                doc.add(table);
                
                 table = new PdfPTable(2);
                // table.getDefaultCell().setBorderWidth(0f)
                 table1 = new PdfPTable(2);
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Name", "center","bold"));
                 table1.addCell(cell1);
                 
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Money", "center","bold"));
                 cell1.disableBorderSide(8);
                 table1.addCell(cell1);
                 
                 
                   array=new JSONArray(jsonObject.getString("credit"));
                  for(int i=0;i<=array.length();i++)
                 {
                     if(i==array.length())
                     {
                     cell1=new PdfPCell();
                    cell1.addElement(new Paragraph("Total"));
                    table1.addCell(cell1);
                    
                     cell1=new PdfPCell();
                     
                     paragraph = new Paragraph(""+creditsum+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell1.addElement(paragraph);
                    cell1.disableBorderSide(8);
                    table1.addCell(cell1);
                     }
                     else
                     {
                     jsondata=new JSONObject(array.getString(i));
                     arrayname=new JSONArray(jsondata.getString("name"));
                    // System.out.print(arrayname.getString(0)+"\n");
                     arraymoney=new JSONArray(jsondata.getString("money"));
                     //System.out.print(arraymoney.getString(0)+"\n");
                     creditsum=creditsum+Integer.parseInt(arraymoney.getString(0));
                     
                    cell1=new PdfPCell();
                    cell1.addElement(new Paragraph(arrayname.getString(0)));
                    table1.addCell(cell1);
                    
                     cell1=new PdfPCell();
                     
                     paragraph = new Paragraph(""+arraymoney.getString(0)+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                    cell1.addElement(paragraph);
                    cell1.disableBorderSide(8);
                    table1.addCell(cell1);
                     }
                 }
                  table.addCell(table1);
                  table2 = new PdfPTable(2);
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Name", "center","bold"));
                 cell1.disableBorderSide(4);
                 table2.addCell(cell1);
                 
                 
                 cell1=new PdfPCell();
                 cell1.addElement(paragraphreturn("Money", "center","bold"));
                 table2.addCell(cell1);
                 
                   array=new JSONArray(jsonObject.getString("debit"));
                  for(int i=0;i<=array.length();i++)
                 {
                     if(i==array.length())
                     {
                     cell1=new PdfPCell();
                    cell1.addElement(new Paragraph("Total"));
                    cell1.disableBorderSide(4);
                    table2.addCell(cell1);
                    
                    cell1=new PdfPCell();
                     paragraph = new Paragraph(""+ debitsum+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                     cell1.addElement(paragraph);
                    table2.addCell(cell1);
                     }
                     else
                     {
                     jsondata=new JSONObject(array.getString(i));
                     arrayname=new JSONArray(jsondata.getString("name"));
                    // System.out.print(arrayname.getString(0)+"\n");
                     arraymoney=new JSONArray(jsondata.getString("money"));
                     //System.out.print(arraymoney.getString(0)+"\n");
                    debitsum=debitsum+Integer.parseInt(arraymoney.getString(0));
                     
                    cell1=new PdfPCell();
                    cell1.addElement(new Paragraph(arrayname.getString(0)));
                    cell1.disableBorderSide(4);
                    table2.addCell(cell1);
                    
                    cell1=new PdfPCell();
                     paragraph = new Paragraph(""+arraymoney.getString(0)+""+"/=", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12));
                     paragraph.setAlignment(Element.ALIGN_RIGHT);
                     cell1.addElement(paragraph);
                    table2.addCell(cell1);
                     }  
                 }
                 table.addCell(table2);
                 table.setSpacingAfter(30f);
                 doc.add(table);
                  
                  sum=creditsum-debitsum;
                 if(sum>=0)
                 {
                   doc.add(paragraphreturn("Capital is:  "+sum+"/=", "center","bold"));
                 }
                 else
                 {
                 doc.add(paragraphreturn("Withdraw is:  "+sum+"/=", "center","bold"));  
                 }
                  
                  paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_CENTER);
                paragraph.setSpacingAfter(30f);
                doc.add(paragraph);
       
                  sum=(projectincome+creditsum)-(projectdebit+debitsum);
                  if(sum>=0)
                 {
                   doc.add(paragraphreturn("Project Income:  "+(projectincome+creditsum)+"/=", "center","bold"));
                   doc.add(paragraphreturn("Project Debit:  "+ (projectdebit+debitsum)+"/=", "center","bold"));
                   doc.add(paragraphreturn("Project Profit is:  "+sum+"/=", "center","bold"));
                 }
                 else
                 {
                   doc.add(paragraphreturn("Project Income:  "+(projectincome+creditsum)+"/=", "center","bold"));
                   doc.add(paragraphreturn("Project Debit:  "+ (projectdebit+debitsum)+"/=", "center","bold"));
                 doc.add(paragraphreturn("Project Loss is:  "+sum+"/=", "center","bold"));  
                 }
                 
                doc.close();
