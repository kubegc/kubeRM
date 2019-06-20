/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.uit.cloud.kubernetes;

import java.util.List;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.DoneableVirtualMachine;
import com.github.kubesys.kubernetes.api.model.VirtualMachine;
import com.github.kubesys.kubernetes.api.model.VirtualMachineList;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/5/1
 *
 * This code is used to manage CustomResource's lifecycle,
 * such as VirtualMachine
 */
public class ExtendedKubernetesClientTest {
	
	public static Config config = new ConfigBuilder()
			.withApiVersion("v1")
			.withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRFNU1EWXhOekV6TURZME9Wb1hEVEk1TURZeE5ERXpNRFkwT1Zvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTDFCCi9ESWd5N08xNlJWbm5kb2VUWmhWMkRFR2w5bHJoUzd3YW1ZQ1MyWGc2Q1BQN2lTM1JtNXRxcEhtMzFXZzR6WHAKVWpueXM0Ry9QQmZsaWJORGdSNmVudWxHM1J1SVdaY25TQnU3Nmh4WWxLNFAxcHlmcThyWjUrTkZiS3pPQjM4MQpZYXhjYnFhWGU4c0lXTFFyQi84QjVtWEE5bHRUUmFCakxWM1NndkEveG1seDdPUkJHSW5EVVY0alc2YVpUR3pHCmp0T1FIbXM3bkxCTDVWUlYyUWhnVDQxRGdnTTl3UlpiTVBoamprYnNWUmJqQ1poS2NHd05rSWNhbFcxRGZOOTcKRFNpVExDM09JOUVocHpmcGZFVi9zaUFBTmFNblFWeVYwZDdoYWRITEtHYUJHRUJkbEpkdEhiaytGWjA2aDJHLwo2SHN4OUNMYzJ2ZGNIMkJCZ2ljQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFHdXFtRXlaa0ZTYjlVM3ZXbit1ejA0VExtKzUKenh1TEo1U3JWM1EweTRjaENwRFphdG8zZURSemtQYXlzbkttYWFUWnFacTM2WVNSTHozemIvS1dTTER2QTBJSApTcFNHN3Z4ejl0YzU5L0g3ZFBTUG5WNENQV2IzKzQ2c1dGVk52QzFnb3dPY0QrazFMdTByM0hnRWRxeXRRVWcyCllGNTcxZlBPQVVlVUI5SmFtMTVNVnl5Q3VPbVJ2QkREaEFERzNla2grZ0dOYnBqYnc0Mzltcy9IS08xcHRpNUwKZHlUOWpUc1hEQkRYWnBYSHFUN0lEN0NEemVkUWFwbVZiZGN5eElReUlNVlcvZnplUTNXQmpnQy9xcVozZVJwSApTMHB0dlJ0RzBzTldJZEI1WnpCSjJVSVNpSVVKakhaL01HQ2p5cGFhVmJoSTkvM0kzS0NWN3VNdzRpVT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJVW9BdTFoYnRpZ0F3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB4T1RBMk1UY3hNekEyTkRsYUZ3MHlNREEyTVRZeE16QTJOVEZhTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQW9sWCtDZmR3cEh5RUtDSFcKNjBLYWhUQmZEQmlxV25kVkp4ZE9nbVpOc05ITnNMM1dEd2ZsRXZtV0FUTEZiZTQya0pyTWN5RWh0Z2JLTElPbQorL3lCM0xFVk1FV1VBV2E5Y0RnU3VrWGV4R0c4QjJqd3NMQlkvZ24xVW1UYVhiM1Rpd25JWUtYelhleHdsUTl0Ckl6UGY0eDBRSGJOeTdjNDBQcng1ejhOb09pejUwWkpnZVBVM0ZudlJETVcrd014M2xqemI3MzF1eXFtRFJTWDkKa1BCOGJXYlYyVVJpM3F3T3NvTUI5RVBab2dLckovTzk4QVV0ZlV5d2xYMmZlR1VoRVN3eDg5OGRhaFBHMjlabQpLSkRxRDE1OVdiSEhlT1RLS3dBcWJQbGZ1RlMyMkk4Y3l4K01xOTRTNXYyUjAwTUE0a1RmT0tOSG9hR0dVVXVFCnN3V1Mwd0lEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFIWWVQa25udnNpQXZCMVM3dlZYYzdFaWtWOHMvNVQ4eWRJZwpUOTFhNlphUElFQWEzQVBBL3JjbmtJZUQ1eHQ0TWZwTXlGMm5hc0NKaVlHSHMvMGtQRGt1czk3czdSazM2VHBPCnNmVGNrLzdFb2luZHgvZWFXRTZzM3ZkSjdnTGxYbzR5NHF3RjBpWUNTNHBLV0g4TVlUSVBwYXVnZ0NoMGp5Y08KTnV4M210VC9qcjlKeHlZR0pqa2JRSjBmdytzcHZsQ0NVZ0JWVDBBWm9veHU2U0pDZEpVU1B3T3BweTQ5ZEhyYQpqU0Qxczd4VWVYMFBiTjN6U2psRDFOZlJmRk9JU3IvYVRmeXJ3N2p4RnZtdnlWSm00THV1ellscjkvQkE3dVU3Cjk0TU0wZHR6OVZjQlUrL1FFRHdULzFqNDVLQ0RoYmhMT3lMS21lSkhpNzI5Slg3ZGFMQT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcFFJQkFBS0NBUUVBb2xYK0NmZHdwSHlFS0NIVzYwS2FoVEJmREJpcVduZFZKeGRPZ21aTnNOSE5zTDNXCkR3ZmxFdm1XQVRMRmJlNDJrSnJNY3lFaHRnYktMSU9tKy95QjNMRVZNRVdVQVdhOWNEZ1N1a1hleEdHOEIyancKc0xCWS9nbjFVbVRhWGIzVGl3bklZS1h6WGV4d2xROXRJelBmNHgwUUhiTnk3YzQwUHJ4NXo4Tm9PaXo1MFpKZwplUFUzRm52UkRNVyt3TXgzbGp6YjczMXV5cW1EUlNYOWtQQjhiV2JWMlVSaTNxd09zb01COUVQWm9nS3JKL085CjhBVXRmVXl3bFgyZmVHVWhFU3d4ODk4ZGFoUEcyOVptS0pEcUQxNTlXYkhIZU9US0t3QXFiUGxmdUZTMjJJOGMKeXgrTXE5NFM1djJSMDBNQTRrVGZPS05Ib2FHR1VVdUVzd1dTMHdJREFRQUJBb0lCQVFDS1VGaHQ1M3o3T1BobApOVWZvRlV5UTFNRDNEb0QvS1pQTkJjZW54VURHdWt2V0hqa25mMk9IZ2s1ejRPMWtsOFczK1p6ZkwwaUl6a291CkdWSUtlMyt1S05kVDFNU2hXUWRwZU8xRDlzb3lhcytKUkxsbTRpcEE4UDByNGJCSWxMZU1USjZFOG9CajgxT04KUGFLUWp0OWdvOSthWHM0Nm5pblhsa0QzYTVmL2ovekhtSW1qcXpVVmRVWlpza1ZjKzk3Q09JbGR2WFJOQWZmTwp2YllHaEw5b0RzN0FEZDlBL2UyN3JBaHU2ZGc2RGEvSDJDMS9YZHlVd1ZyL0xPcnFCTHpkcDhlZEJCRHFzTnppCjZSOVN1OXZvZFN5b0lmM2QvNzlmc1RsVzlnMzBHS1JlNW5Wa0NpdkE5MXEwMFpKNUNkdldjaGh5L1ZybGdDei8KVmhVWkZ6OHhBb0dCQU5hSFNFTHB5Mi9UN2t3eVdIUWlNZFl5Q0hUbWxyMnVUNGgxN1JQRkcyNHh1MFM4YjJLdApuUHhsemtkM1h0R0dDLzZEUzJvOE1hRDFFZHcvK0hhUS9HWXV4RW91bk0zRlFyS3Q5ajZETEN3SHMrQlJqNmRpClFPM3pFK2EyNG1nTlpKeHFBS3Z0OVFsOFNGWG83bHV4Z0NsWHA5SGgzUWNvRHpZTHZBQ0ZxU21KQW9HQkFNRzMKeGVyTUtGU3NmTnYvWThoZytOaG9CSXFDVzRDKzNyeXkwWHNnbFpteGRpbGZhb0lPVDdBdnJXTlZybVJEMHBOdwpiTjRhencwN3FadkZkQ3MzQWprb0Q3ek15S25VS3VYWklqYWpiak8xK1JHOW9wQXNTbFc0NnRWOTZhWWprTDd1CkE1QURtZEdMbnRYckM3ZDEvaHVXNURFazlYNEFRZ01JVDFOQkNDNTdBb0dBYWZhS1g5ckJUS3crMFU0dzJMUW4KTDZpbytxMEhHZ3ZtWnYzd3EwbzV3VlU1TGJ0eHZTa0tHTFZralhKTjJHcEF0R2Mvc2VxbmpOeGdCUTFuK1pDbAp5d2NnbDQrc2pncjRaZTk5YU9VN3hVZk10bWt5N3JlZVlLb25VMWRVUDVjVjhoaDkybTZBRVQ1ZE1Hb01paWdECnFPZ09FYUpoblc1UkRSNEFyN1ZWbUJrQ2dZRUF2RWoxNnFCaXR1SXNQZHgrVE4zTUlQTUJMdXRES3VEYU40aUsKeVdWRFl1NDB6a082and4LzhkeDFaNjU0NFdWMmYrM3ZNdlhWdDZQODE3WFVrc2hWdGQ0MVphbVpoRS8vSGdhdApGWFJHQWFibW5wSzJOZkdHa3NrNEh5ZlIrTFJxMU5rY3gxWm9wRjVUbE5KdlFmTkNkK2h5aFQzS0NwMFBqRWtBCmFRZzFmbzhDZ1lFQTAxMU1FVk9MY0Q2Smk0Z0I3SlEvaWlTRXNjYUlSQUxEblI0NmcxQW5RLzM2Z1ZyRGptREsKc1ZYdGVvV0JrWDhtZGR6ZXE1WThjcDB3VFJqM0x4RFNna3pnNmxnWWttdS80bkRhck8yRncvV2dueTFWRkFEOQoyOG5LbHVMWWFNamlLY09oRDZHOUI3NDVNNjB1VmNHckdEb0hiUXViZk1vWDNxdnJPK1hVMjVRPQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=")
			.withMasterUrl("https://10.25.0.135:6443")
			.build();
	
	public static void main(String[] args) throws Exception {

		ExtendedKubernetesClient client = new ExtendedKubernetesClient(config);

//		getPods(client);
		watchVM(client);
//		DefaultKubernetesClient client = new DefaultKubernetesClient(config);
//		KubernetesDeserializer.registerCustomKind("cloudplus.io/v1alpha3", "VirtualMachine", VirtualMachine.class);
//		CustomResourceDefinition crd = client.customResourceDefinitions().withName("virtualmachines.cloudplus.io").get();
//		
//		System.out.println(crd);
//
//		client.customResource(crd, VirtualMachine.class, VirtualMachineList.class, DoneableVirtualMachine.class)
//						.inAnyNamespace().watch(new Watcher<VirtualMachine>() {
//
//				public void eventReceived(Action action, VirtualMachine resource) {
//					System.out.println("-" + resource);
//				}
//
//				public void onClose(KubernetesClientException cause) {
//					System.out.println(cause);
//				}
//				
//			});
	}

	protected static void listVM(ExtendedKubernetesClient client, CustomResourceDefinition crd) {
		List<VirtualMachine> vms = client.customResource(crd, VirtualMachine.class, 
														VirtualMachineList.class, 
														DoneableVirtualMachine.class)
						.inAnyNamespace().list().getItems();
		for (VirtualMachine vm : vms) {
			System.out.println(vm);
		}
	}

	protected static void watchVM(ExtendedKubernetesClient client) {
		Watcher<VirtualMachine> watcher = new Watcher<VirtualMachine>() {

			public void eventReceived(Action action, VirtualMachine resource) {
				System.out.println(action + ":" + resource);
			}

			public void onClose(KubernetesClientException cause) {
				System.out.println(cause);
			}
			
		};
		client.watchVirtualMachine(watcher );
	}

	protected static void getPods(ExtendedKubernetesClient client) {
		for (Pod pod : client.pods().inAnyNamespace().list().getItems()) {
			System.out.println(pod.getMetadata().getName());
		}
	}
	
}
