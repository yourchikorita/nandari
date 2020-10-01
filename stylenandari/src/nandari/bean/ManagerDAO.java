package nandari.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;






public class ManagerDAO {
	private static ManagerDAO instance = new ManagerDAO();

	// MngrDBBean객체를 리턴하는 메소드
	public static ManagerDAO getInstance() {
		return instance;
	}

	private ManagerDAO() {
	}
	// 커넥션 풀에서 커넥션 객체를 얻어내는 메소드

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/pool");
		conn = ds.getConnection();
		return conn;
	}
	// 관리자 인증 메서드
	public int userCheck(String id, String passwd) {
		int check = -1;
		try {
			conn = getConnection();

			String sql = "SELECT managerPw FROM manager WHERE managerId=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// 해당 아이디가 있으면 수행
			if (rs.next()) {
				String dbpw = rs.getString("managerPw");
				if (passwd.equals(dbpw)) {
					check = 1;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		System.out.println("[check]" + check);
		return check;
	}
	
	// 등록된 책의 정보를 수정시 사용하는 메소드
    public void updateItem(ManageItemBean item, int item_id) {
        
    	try {
            conn = getConnection();
            
            String sql = "UPDATE item SET item_kind=?,item_name=?,item_price=?";
            sql += ",item_count=?";
            sql += ",item_image=?,item_summary=?,discount_rate=?";
            sql += " where item_id=?";
            
            pstmt = conn.prepareStatement(sql);

          
			
			pstmt.setString(1, item.getItem_kind());
			pstmt.setString(2, item.getItem_name());
			pstmt.setInt(3, item.getItem_price());
			pstmt.setInt(4, item.getItem_count());
			pstmt.setString(5, item.getItem_image());
			pstmt.setString(6, item.getItem_summary());
			pstmt.setInt(7, item.getDiscount_rate());
			pstmt.setInt(8, item_id);
			
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	if (pstmt != null) try{ pstmt.close(); }catch(SQLException e) {}
        	if (conn != null) try{ conn.close(); }catch(SQLException e) {}
        }
    }
    
    
    // itemId에 해당하는 책의 정보를 삭제시 사용하는 메소드
    public void deleteItem(int itemId) {
        
        try {
        	
			conn = getConnection();
			
			String sql = "DELETE FROM item WHERE item_id=?"; 
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,itemId);
            
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	if (rs != null) try{ rs.close(); }catch(SQLException e) {}
        	if (pstmt != null) try{ pstmt.close(); }catch(SQLException e) {}
        	if (conn != null) try{ conn.close(); }catch(SQLException e) {}
        }
    }
    
    
	// 책 등록 메서드
		public void insertItem(ManageItemBean item) {

			int num = 0;

			try {
				conn = getConnection();

				// num의 최대값을 꺼내와 1증가 시키기
				String sql = "SELECT COUNT(item_id) FROM item";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					num = rs.getInt(1) + 1;
				}

				sql = "INSERT INTO item(item_id,item_kind,item_name,item_price,";
				sql += "item_count,item_image,";
				sql += "item_summary,discount_rate,reg_date) VALUES (?,?,?,?,?,?,?,?,now())";

				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, num);
				pstmt.setString(2, item.getItem_kind());
				pstmt.setString(3, item.getItem_name());
				pstmt.setInt(4, item.getItem_price());
				pstmt.setInt(5, item.getItem_count());
				pstmt.setString(6, item.getItem_image());
				pstmt.setString(7, item.getItem_summary());
				pstmt.setInt(8, item.getDiscount_rate());

				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
					}
			}
		}
		
		
	public List<ManageItemBean> getItems(String item_kind) {

		List<ManageItemBean> itemList = null;

		try {

			conn = getConnection();

			String sql1 = "SELECT * FROM item";
			String sql2 = "SELECT * FROM item ";
			sql2 += "WHERE item_kind=? ORDER BY reg_date DESC";

			if (item_kind.equals("all")) {
				pstmt = conn.prepareStatement(sql1);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, item_kind);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				itemList = new ArrayList<ManageItemBean>();
				do {
					ManageItemBean item = new ManageItemBean();

					item.setItem_id(rs.getInt("item_id"));
     				item.setItem_kind(rs.getString("item_kind"));
     				item.setItem_name(rs.getString("item_name"));
     				item.setItem_price(rs.getInt("item_price"));
     				item.setItem_count(rs.getInt("item_count"));
     				item.setItem_image(rs.getString("item_image"));
     				item.setDiscount_rate(rs.getInt("discount_rate"));
     				item.setReg_date(rs.getString("reg_date"));
     				item.setItem_summary(rs.getString("item_summary"));
     		
					itemList.add(item);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return itemList;
	}

	
	
	// 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간책목록을 얻어내는 메소드
	 	public ManageItemBean[] getItems(String item_kind, int count) {
	 		ManageItemBean itemList[] =null;
	 		int i=0;
	 		try {
	 			 conn = getConnection();
	             
	             String sql = "SELECT * FROM item WHERE item_kind=? ";
	             sql += "ORDER  BY reg_date DESC LIMIT ?,?";
	             
	             pstmt = conn.prepareStatement(sql);
	             pstmt.setString(1, item_kind);
	             pstmt.setInt(2, 0);
	             pstmt.setInt(3, count);
	             
	             rs = pstmt.executeQuery();
	             
	             if(rs.next()) {
	            	 
	            	 itemList = new ManageItemBean[count];
	            	 
	            	 do {
	            		 	ManageItemBean item = new ManageItemBean();
	            		 
	            		 	item.setItem_id(rs.getInt("item_id"));
		     				item.setItem_kind(rs.getString("item_kind"));
		     				item.setItem_name(rs.getString("item_name"));
		     				item.setItem_price(rs.getInt("item_price"));
		     				item.setItem_count(rs.getInt("item_count"));
		     				item.setItem_image(rs.getString("item_image"));
		     				item.setDiscount_rate(rs.getInt("discount_rate"));
		     				item.setReg_date(rs.getString("reg_date"));
		     				item.setItem_summary(rs.getString("item_summary"));
		     				
		     				itemList[i] = item;
		     				i++;
	            	 }while(rs.next());
	             }
	           
	 		}catch(Exception e) {
	 			e.printStackTrace();
	 		}finally {
	 			if (rs != null) try{ rs.close(); }catch(SQLException e) {}
	         	if (pstmt != null) try{ pstmt.close(); }catch(SQLException e) {}
	         	if (conn != null) try{ conn.close(); }catch(SQLException e) {}
	 		}
	 	return itemList;
	 	}
	
	 // 해당 분류의 아이템 의 수를 얻어내는 메소드
	 		public int getItemCount(String item_kind)
	 		throws Exception {
	 		    Connection conn = null;
	 		    PreparedStatement pstmt = null;
	 		    ResultSet rs = null;

	 		    int x=0;
	 		    int kind  = Integer.parseInt(item_kind);

	 		    try {
	 		        conn = getConnection();
	 		        String query = "select count(*) from item where item_kind=" + kind;
	 		        pstmt = conn.prepareStatement(query);
	 		        rs = pstmt.executeQuery();

	 		        if (rs.next()) 
	 		            x= rs.getInt(1);
	 		    } catch(Exception ex) {
	 		        ex.printStackTrace();
	 		    } finally {
	 		        if (rs != null) 
	 		           try { rs.close(); } catch(SQLException ex) {}
	 		        if (pstmt != null) 
	 		           try { pstmt.close(); } catch(SQLException ex) {}
	 		        if (conn != null) 
	 		           try { conn.close(); } catch(SQLException ex) {}
	 		    }
	 			return x;
	 		}
	
	 	// 전체 등록된 아이템 의 수를 얻어내는 메서드
	 		public int getItemCount() {

	 			int count = 0;

	 			try {
	 				conn = getConnection();

	 				String sql = "SELECT COUNT(*) FROM item";
	 				pstmt = conn.prepareStatement(sql);
	 				rs = pstmt.executeQuery();

	 				if (rs.next()) {
	 					count = rs.getInt(1);
	 				}
	 			} catch (Exception e) {
	 				e.printStackTrace();
	 			} finally {
	 				if (rs != null)
	 					try {
	 						rs.close();
	 					} catch (SQLException e) {
	 					}
	 				if (pstmt != null)
	 					try {
	 						pstmt.close();
	 					} catch (SQLException e) {
	 					}
	 				if (conn != null)
	 					try {
	 						conn.close();
	 					} catch (SQLException e) {
	 					}
	 			}
	 			return count;
	 		}
	
	
	 		// itemId에 해당하는 아이템의 정보를 얻어내는 메소드로
	 		// 등록된 아이템을 수정하기 위해 수정폼으로 읽어들기이기 위한 메서드
	 		public ManageItemBean getItem(int itemId) {

	 			ManageItemBean item = null;

	 			try {
	 				conn = getConnection();

	 				String sql = "SELECT * FROM item WHERE item_id=?";
	 				pstmt = conn.prepareStatement(sql);
	 				pstmt.setInt(1, itemId);

	 				rs = pstmt.executeQuery();

	 				if (rs.next()) {

	 					item = new ManageItemBean();

	 					item.setItem_id(rs.getInt("item_id"));
	     				item.setItem_kind(rs.getString("item_kind"));
	     				item.setItem_name(rs.getString("item_name"));
	     				item.setItem_price(rs.getInt("item_price"));
	     				item.setItem_count(rs.getInt("item_count"));
	     				item.setItem_image(rs.getString("item_image"));
	     				item.setDiscount_rate(rs.getInt("discount_rate"));
	     				item.setReg_date(rs.getString("reg_date"));
	     				item.setItem_summary(rs.getNString("item_summary"));
	     					}
	 			} catch (Exception e) {
	 				e.printStackTrace();
	 			} finally {
	 				if (rs != null)
	 					try {
	 						rs.close();
	 					} catch (SQLException e) {
	 					}
	 				if (pstmt != null)
	 					try {
	 						pstmt.close();
	 					} catch (SQLException e) {
	 					}
	 				if (conn != null)
	 					try {
	 						conn.close();
	 					} catch (SQLException e) {
	 					}
	 			}
	 			return item;
	 		}
	
	
	
	
	
	
	
	}

