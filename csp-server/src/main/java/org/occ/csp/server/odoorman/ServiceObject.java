package org.occ.csp.server.odoorman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.occ.csp.domain.ChurchMember;
import org.occ.csp.domain.Fellowship;
import org.occ.csp.domain.Footprint;
import org.occ.csp.service.CspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ServiceObject extends Thread {
	@Autowired
	CspService cspService;
	
	DataInputStream dis;
	DataOutputStream dos;
	
	public ServiceObject() {}
	
	public void initWithSocket(Socket socket) {
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			//start thread
			if (!this.isAlive())
				start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void run() {
		String result = "";

		try {
			while (true) {
				//get data from client
				int count = dis.available();
				byte[] payload = new byte[count];
				dis.read(payload);
				String datas = new String(payload);
				//if (datas.length()==0) System.out.println("datas is empty.");
				if (datas.length()==0) continue;

				System.out.println("echo:\n" + datas);
				
				//data : action=queryAllFellowships;
				//data : action=checkin;memberId=1
				//data : action=queryChurchMembersByFellowshipId;fellowshipId=1
				String[] paramsSplit = datas.split(";");
				String command = "";
				for (int index=0 ;index < paramsSplit.length; index++) {
					command = paramsSplit[0].split("=")[1];
					System.out.println("=====command is : " + command);
				}
				
				if (command.equals("checkin")) {
					String memberId = paramsSplit[1].split("=")[1];
					executeCheckin(memberId);
				} else if (command.equals("queryAllFellowships")) {
					result = executeQueryAllFellowships();
				} else if (command.equals("queryChurchMembersByFellowshipId")) {
					String fellowshipId = paramsSplit[1].split("=")[1];
					result = executeQueryChurchMembersByFellowshipId(fellowshipId);
				} else if (command.equals("executeQueryChurchMemberDtlById")) {
					String memberId = paramsSplit[1].split("=")[1];
					result = executeQueryChurchMemberDtlById(memberId);
				}
				
				//statusCode = "00";
				System.out.println("response:\n" + result);
				log(URLEncoder.encode(result, "UTF-8"));

				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "{\"fault:\"" + e.getMessage() + "\"}";
			log(result);
		}
	}
	
	private String executeQueryAllFellowships() {
		//"{\"result\":{\"1\":[\"1\",\"area1\"],\"2\":[\"2\",\"area2\"]}}";
		StringBuilder builder = new StringBuilder("{\"result\":{");
		List<Fellowship> fellowships;
		try {
			fellowships = cspService.getAllFellowships();
			for (int index=0; index < fellowships.size(); index++) {
				Fellowship f = fellowships.get(index);
				String code = f.getFellowshipId();
				String name = f.getFellowshipName();
				String regionId = f.getRegion().getRegionId();
				Date createDate = f.getCreateDate();
				Date updateDate = f.getUpdateDate();
				Date dataDate = updateDate == null?createDate:updateDate;
				String strDataTime = String.valueOf(dataDate.getTime());
				builder.append("\""+code+"\":[");
				builder.append("\""+code+"\",\""+name+"\",\""+regionId+"\",\""+strDataTime+"\"],");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = builder.substring(0, builder.length()-1);
		result = result + "}}";
		return result;			
	}

	private String executeQueryChurchMembersByFellowshipId(String fellowshipId) {
		//"{\"result\":{\"1\":[\"1\",\"name1\"],\"2\":[\"1\",\"name2\"]}}";
		StringBuilder builder = new StringBuilder("{\"result\":{");
		List<ChurchMember> churchMembers;
		try {
			churchMembers = cspService.getChurchMembersByFellowship(fellowshipId);
			for (int index=0; index < churchMembers.size(); index++) {
				ChurchMember c = churchMembers.get(index);
				String code = c.getMemberSid();
				String name = c.getMemberName();
				//TODO
				//Date createDate = c.getCreateDate();
				//Date updateDate = c.getUpdateDate();
				//Date dataDate = updateDate == null?createDate:updateDate;
				Date createDate = new Date();
				Date updateDate = new Date();
				Date dataDate =  updateDate == null?createDate:updateDate;
				String strDataTime = String.valueOf(dataDate.getTime());
				builder.append("\""+code+"\":[");
				builder.append("\""+code+"\",\""+name+"\",\""+fellowshipId+"\",\""+strDataTime+"\"],");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = builder.substring(0, builder.length()-1);
		result = result + "}}";
		return result;
	}
	
	private String executeQueryChurchMemberDtlById(String churchMemberId) {
		//"{\"result\":{\"1\":[\"1\",\"name1\",\"0981170518\",\"62/10/16\",\"yefang.wong@gmail.com\",
		//\"address\",\"https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/c18.18.230.230/s160x160/1174598_10201111834932288_234606359_n.jpg\"]}}";
		StringBuilder builder = new StringBuilder("{\"result\":{");
		ChurchMember churchMember;
		try {
			churchMember = cspService.getChurchMemberById(churchMemberId);
			String sid = churchMemberId;
			String name = churchMember.getMemberName();
			String phone = churchMember.getDtl().getTel();
			Date birthday = churchMember.getDtl().getBirthday();
			String email = churchMember.getDtl().getEmail();
			String address = churchMember.getDtl().getAddress();
			String photoUrl = churchMember.getDtl().getPhotoUrl();
			Date createDate = churchMember.getCreateDate();
			Date updateDate = churchMember.getUpdateDate();
			Date dataDate = updateDate == null?createDate:updateDate;
			String strDataTime = String.valueOf(dataDate.getTime());
			builder.append("\""+sid+"\":[");
			builder.append("\""+sid+"\",\""+name+"\",\""+phone+"\",\""+birthday+
					"\",\""+email+"\",\""+address+"\",\""+photoUrl+"\",\""+strDataTime+"\"]}}");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = builder.substring(0, builder.length()-1);
		result = result + "}}";
		return result;
	}


	private void executeCheckin(String memberId) {
		Footprint f;
		f = new Footprint();
		try {
			f.setFootprintId(cspService.getMaxFootprintId());
			f.setMemberSid(memberId);
			f.setLoginDate(new Date());
			f.setCreateUid("9999");
			f.setCreateDate(new Date());
			cspService.saveFootprint(f);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(f.toString());
	}

	private void log(String msg) {
		try {
			byte[] payload = msg.getBytes();
			int len = payload.length;
			dos.writeInt(len);
			if (len > 0) {
				dos.write(payload);
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}
}
