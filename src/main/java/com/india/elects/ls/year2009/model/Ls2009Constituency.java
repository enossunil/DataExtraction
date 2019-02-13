package com.india.elects.ls.year2009.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ls_2014 database table.
 * 
 */
@Entity
@Table(name="ls_2009")
@NamedQuery(name="Ls2009Constituency.findAll", query="SELECT l FROM Ls2009Constituency l")
public class Ls2009Constituency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="constituency_cd")
	private Integer constituencyCd;

	@Column(name="constituency_name")
	private String constituencyName;

	@Column(name="contested_men")
	private Integer contestedMen;

	@Column(name="contested_other")
	private Integer contestedOther;

	@Column(name="contested_total")
	private Integer contestedTotal;

	@Column(name="contested_women")
	private Integer contestedWomen;

	@Temporal(TemporalType.DATE)
	@Column(name="counting_date")
	private Date countingDate;

	@Column(name="electors_general_men")
	private Integer electorsGeneralMen;

	@Column(name="electors_general_other")
	private Integer electorsGeneralOther;

	@Column(name="electors_general_total")
	private Integer electorsGeneralTotal;

	@Column(name="electors_general_women")
	private Integer electorsGeneralWomen;

	@Column(name="electors_overseas_men")
	private Integer electorsOverseasMen;

	@Column(name="electors_overseas_other")
	private Integer electorsOverseasOther;

	@Column(name="electors_overseas_total")
	private Integer electorsOverseasTotal;

	@Column(name="electors_overseas_women")
	private Integer electorsOverseasWomen;

	@Column(name="electors_service_men")
	private Integer electorsServiceMen;

	@Column(name="electors_service_other")
	private Integer electorsServiceOther;

	@Column(name="electors_service_total")
	private Integer electorsServiceTotal;

	@Column(name="electors_service_women")
	private Integer electorsServiceWomen;

	@Column(name="electors_total")
	private Integer electorsTotal;

	@Column(name="electors_total_men")
	private Integer electorsTotalMen;

	@Column(name="electors_total_other")
	private Integer electorsTotalOther;

	@Column(name="electors_total_women")
	private Integer electorsTotalWomen;

	@Column(name="forfeited_deposit_men")
	private Integer forfeitedDepositMen;

	@Column(name="forfeited_deposit_other")
	private Integer forfeitedDepositOther;

	@Column(name="forfeited_deposit_total")
	private Integer forfeitedDepositTotal;

	@Column(name="forfeited_deposit_women")
	private Integer forfeitedDepositWomen;

	private Integer margin;

	@Column(name="nominated_men")
	private Integer nominatedMen;

	@Column(name="nominated_other")
	private Integer nominatedOther;

	@Column(name="nominated_rejected_men")
	private Integer nominatedRejectedMen;

	@Column(name="nominated_rejected_other")
	private Integer nominatedRejectedOther;

	@Column(name="nominated_rejected_total")
	private Integer nominatedRejectedTotal;

	@Column(name="nominated_rejected_women")
	private Integer nominatedRejectedWomen;

	@Column(name="nominated_total")
	private Integer nominatedTotal;

	@Column(name="nominated_women")
	private Integer nominatedWomen;

	@Column(name="polled_deducted_evm")
	private Integer polledDeductedEvm;

	@Column(name="polled_evm")
	private Integer polledEvm;

	@Column(name="polled_nota")
	private Integer polledNota;

	@Column(name="polled_postal")
	private Integer polledPostal;

	@Column(name="polled_postal_invalid")
	private Integer polledPostalInvalid;

	@Column(name="polled_postal_valid")
	private Integer polledPostalValid;

	@Column(name="polled_tendered_votes")
	private Integer polledTenderedVotes;

	@Column(name="polled_test_evm")
	private Integer polledTestEvm;

	@Column(name="polled_valid_evm")
	private Integer polledValidEvm;

	@Column(name="polled_valid_total")
	private Integer polledValidTotal;

	@Temporal(TemporalType.DATE)
	@Column(name="polling_date")
	private Date pollingDate;

	@Column(name="polling_station_count")
	private Integer pollingStationCount;

	@Column(name="polling_station_electoral_avg_cnt")
	private Integer pollingStationElectoralAvgCnt;

	@Column(name="repoll_booth_count")
	private Integer repollBoothCount;

	@Temporal(TemporalType.DATE)
	@Column(name="repoll_date")
	private Date repollDate;

	@Temporal(TemporalType.DATE)
	@Column(name="result_date")
	private Date resultDate;

	@Column(name="result_runner_candidate")
	private String resultRunnerCandidate;

	@Column(name="result_runner_party")
	private String resultRunnerParty;

	@Column(name="result_runner_vote")
	private Integer resultRunnerVote;

	@Column(name="result_winner_candidate")
	private String resultWinnerCandidate;

	@Column(name="result_winner_party")
	private String resultWinnerParty;

	@Column(name="result_winner_vote")
	private Integer resultWinnerVote;

	@Column(name="state_cd")
	private String stateCd;

	@Column(name="state_name")
	private String stateName;

	@Column(name="voters_general_men")
	private Integer votersGeneralMen;

	@Column(name="voters_general_others")
	private Integer votersGeneralOthers;

	@Column(name="voters_general_total")
	private Integer votersGeneralTotal;

	@Column(name="voters_general_women")
	private Integer votersGeneralWomen;

	@Column(name="voters_men")
	private Integer votersMen;

	@Column(name="voters_others")
	private Integer votersOthers;

	@Column(name="voters_overseas_men")
	private Integer votersOverseasMen;

	@Column(name="voters_overseas_others")
	private Integer votersOverseasOthers;

	@Column(name="voters_overseas_total")
	private Integer votersOverseasTotal;

	@Column(name="voters_overseas_women")
	private Integer votersOverseasWomen;

	@Column(name="voters_postal_men")
	private Integer votersPostalMen;

	@Column(name="voters_postal_others")
	private Integer votersPostalOthers;

	@Column(name="voters_postal_total")
	private Integer votersPostalTotal;

	@Column(name="voters_postal_women")
	private Integer votersPostalWomen;

	@Column(name="voters_proxy_men")
	private Integer votersProxyMen;

	@Column(name="voters_proxy_others")
	private Integer votersProxyOthers;

	@Column(name="voters_proxy_total")
	private Integer votersProxyTotal;

	@Column(name="voters_proxy_women")
	private Integer votersProxyWomen;

	@Column(name="voters_rejected_other_reasons")
	private Integer votersRejectedOtherReasons;

	@Column(name="voters_total")
	private Integer votersTotal;

	@Column(name="voters_women")
	private Integer votersWomen;

	@Column(name="withdrawn_men")
	private Integer withdrawnMen;

	@Column(name="withdrawn_other")
	private Integer withdrawnOther;

	@Column(name="withdrawn_total")
	private Integer withdrawnTotal;

	@Column(name="withdrawn_women")
	private Integer withdrawnWomen;

	public Ls2009Constituency() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConstituencyCd() {
		return this.constituencyCd;
	}

	public void setConstituencyCd(Integer constituencyCd) {
		this.constituencyCd = constituencyCd;
	}

	public String getConstituencyName() {
		return this.constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public Integer getContestedMen() {
		return this.contestedMen;
	}

	public void setContestedMen(Integer contestedMen) {
		this.contestedMen = contestedMen;
	}

	public Integer getContestedOther() {
		return this.contestedOther;
	}

	public void setContestedOther(Integer contestedOther) {
		this.contestedOther = contestedOther;
	}

	public Integer getContestedTotal() {
		return this.contestedTotal;
	}

	public void setContestedTotal(Integer contestedTotal) {
		this.contestedTotal = contestedTotal;
	}

	public Integer getContestedWomen() {
		return this.contestedWomen;
	}

	public void setContestedWomen(Integer contestedWomen) {
		this.contestedWomen = contestedWomen;
	}

	public Date getCountingDate() {
		return this.countingDate;
	}

	public void setCountingDate(Date countingDate) {
		this.countingDate = countingDate;
	}

	public Integer getElectorsGeneralMen() {
		return this.electorsGeneralMen;
	}

	public void setElectorsGeneralMen(Integer electorsGeneralMen) {
		this.electorsGeneralMen = electorsGeneralMen;
	}

	public Integer getElectorsGeneralOther() {
		return this.electorsGeneralOther;
	}

	public void setElectorsGeneralOther(Integer electorsGeneralOther) {
		this.electorsGeneralOther = electorsGeneralOther;
	}

	public Integer getElectorsGeneralTotal() {
		return this.electorsGeneralTotal;
	}

	public void setElectorsGeneralTotal(Integer electorsGeneralTotal) {
		this.electorsGeneralTotal = electorsGeneralTotal;
	}

	public Integer getElectorsGeneralWomen() {
		return this.electorsGeneralWomen;
	}

	public void setElectorsGeneralWomen(Integer electorsGeneralWomen) {
		this.electorsGeneralWomen = electorsGeneralWomen;
	}

	public Integer getElectorsOverseasMen() {
		return this.electorsOverseasMen;
	}

	public void setElectorsOverseasMen(Integer electorsOverseasMen) {
		this.electorsOverseasMen = electorsOverseasMen;
	}

	public Integer getElectorsOverseasOther() {
		return this.electorsOverseasOther;
	}

	public void setElectorsOverseasOther(Integer electorsOverseasOther) {
		this.electorsOverseasOther = electorsOverseasOther;
	}

	public Integer getElectorsOverseasTotal() {
		return this.electorsOverseasTotal;
	}

	public void setElectorsOverseasTotal(Integer electorsOverseasTotal) {
		this.electorsOverseasTotal = electorsOverseasTotal;
	}

	public Integer getElectorsOverseasWomen() {
		return this.electorsOverseasWomen;
	}

	public void setElectorsOverseasWomen(Integer electorsOverseasWomen) {
		this.electorsOverseasWomen = electorsOverseasWomen;
	}

	public Integer getElectorsServiceMen() {
		return this.electorsServiceMen;
	}

	public void setElectorsServiceMen(Integer electorsServiceMen) {
		this.electorsServiceMen = electorsServiceMen;
	}

	public Integer getElectorsServiceOther() {
		return this.electorsServiceOther;
	}

	public void setElectorsServiceOther(Integer electorsServiceOther) {
		this.electorsServiceOther = electorsServiceOther;
	}

	public Integer getElectorsServiceTotal() {
		return this.electorsServiceTotal;
	}

	public void setElectorsServiceTotal(Integer electorsServiceTotal) {
		this.electorsServiceTotal = electorsServiceTotal;
	}

	public Integer getElectorsServiceWomen() {
		return this.electorsServiceWomen;
	}

	public void setElectorsServiceWomen(Integer electorsServiceWomen) {
		this.electorsServiceWomen = electorsServiceWomen;
	}

	public Integer getElectorsTotal() {
		return this.electorsTotal;
	}

	public void setElectorsTotal(Integer electorsTotal) {
		this.electorsTotal = electorsTotal;
	}

	public Integer getElectorsTotalMen() {
		return this.electorsTotalMen;
	}

	public void setElectorsTotalMen(Integer electorsTotalMen) {
		this.electorsTotalMen = electorsTotalMen;
	}

	public Integer getElectorsTotalOther() {
		return this.electorsTotalOther;
	}

	public void setElectorsTotalOther(Integer electorsTotalOther) {
		this.electorsTotalOther = electorsTotalOther;
	}

	public Integer getElectorsTotalWomen() {
		return this.electorsTotalWomen;
	}

	public void setElectorsTotalWomen(Integer electorsTotalWomen) {
		this.electorsTotalWomen = electorsTotalWomen;
	}

	public Integer getForfeitedDepositMen() {
		return this.forfeitedDepositMen;
	}

	public void setForfeitedDepositMen(Integer forfeitedDepositMen) {
		this.forfeitedDepositMen = forfeitedDepositMen;
	}

	public Integer getForfeitedDepositOther() {
		return this.forfeitedDepositOther;
	}

	public void setForfeitedDepositOther(Integer forfeitedDepositOther) {
		this.forfeitedDepositOther = forfeitedDepositOther;
	}

	public Integer getForfeitedDepositTotal() {
		return this.forfeitedDepositTotal;
	}

	public void setForfeitedDepositTotal(Integer forfeitedDepositTotal) {
		this.forfeitedDepositTotal = forfeitedDepositTotal;
	}

	public Integer getForfeitedDepositWomen() {
		return this.forfeitedDepositWomen;
	}

	public void setForfeitedDepositWomen(Integer forfeitedDepositWomen) {
		this.forfeitedDepositWomen = forfeitedDepositWomen;
	}

	public Integer getMargin() {
		return this.margin;
	}

	public void setMargin(Integer margin) {
		this.margin = margin;
	}

	public Integer getNominatedMen() {
		return this.nominatedMen;
	}

	public void setNominatedMen(Integer nominatedMen) {
		this.nominatedMen = nominatedMen;
	}

	public Integer getNominatedOther() {
		return this.nominatedOther;
	}

	public void setNominatedOther(Integer nominatedOther) {
		this.nominatedOther = nominatedOther;
	}

	public Integer getNominatedRejectedMen() {
		return this.nominatedRejectedMen;
	}

	public void setNominatedRejectedMen(Integer nominatedRejectedMen) {
		this.nominatedRejectedMen = nominatedRejectedMen;
	}

	public Integer getNominatedRejectedOther() {
		return this.nominatedRejectedOther;
	}

	public void setNominatedRejectedOther(Integer nominatedRejectedOther) {
		this.nominatedRejectedOther = nominatedRejectedOther;
	}

	public Integer getNominatedRejectedTotal() {
		return this.nominatedRejectedTotal;
	}

	public void setNominatedRejectedTotal(Integer nominatedRejectedTotal) {
		this.nominatedRejectedTotal = nominatedRejectedTotal;
	}

	public Integer getNominatedRejectedWomen() {
		return this.nominatedRejectedWomen;
	}

	public void setNominatedRejectedWomen(Integer nominatedRejectedWomen) {
		this.nominatedRejectedWomen = nominatedRejectedWomen;
	}

	public Integer getNominatedTotal() {
		return this.nominatedTotal;
	}

	public void setNominatedTotal(Integer nominatedTotal) {
		this.nominatedTotal = nominatedTotal;
	}

	public Integer getNominatedWomen() {
		return this.nominatedWomen;
	}

	public void setNominatedWomen(Integer nominatedWomen) {
		this.nominatedWomen = nominatedWomen;
	}

	public Integer getPolledDeductedEvm() {
		return this.polledDeductedEvm;
	}

	public void setPolledDeductedEvm(Integer polledDeductedEvm) {
		this.polledDeductedEvm = polledDeductedEvm;
	}

	public Integer getPolledEvm() {
		return this.polledEvm;
	}

	public void setPolledEvm(Integer polledEvm) {
		this.polledEvm = polledEvm;
	}

	public Integer getPolledNota() {
		return this.polledNota;
	}

	public void setPolledNota(Integer polledNota) {
		this.polledNota = polledNota;
	}

	public Integer getPolledPostal() {
		return this.polledPostal;
	}

	public void setPolledPostal(Integer polledPostal) {
		this.polledPostal = polledPostal;
	}

	public Integer getPolledPostalInvalid() {
		return this.polledPostalInvalid;
	}

	public void setPolledPostalInvalid(Integer polledPostalInvalid) {
		this.polledPostalInvalid = polledPostalInvalid;
	}

	public Integer getPolledPostalValid() {
		return this.polledPostalValid;
	}

	public void setPolledPostalValid(Integer polledPostalValid) {
		this.polledPostalValid = polledPostalValid;
	}

	public Integer getPolledTenderedVotes() {
		return this.polledTenderedVotes;
	}

	public void setPolledTenderedVotes(Integer polledTenderedVotes) {
		this.polledTenderedVotes = polledTenderedVotes;
	}

	public Integer getPolledTestEvm() {
		return this.polledTestEvm;
	}

	public void setPolledTestEvm(Integer polledTestEvm) {
		this.polledTestEvm = polledTestEvm;
	}

	public Integer getPolledValidEvm() {
		return this.polledValidEvm;
	}

	public void setPolledValidEvm(Integer polledValidEvm) {
		this.polledValidEvm = polledValidEvm;
	}

	public Integer getPolledValidTotal() {
		return this.polledValidTotal;
	}

	public void setPolledValidTotal(Integer polledValidTotal) {
		this.polledValidTotal = polledValidTotal;
	}

	public Date getPollingDate() {
		return this.pollingDate;
	}

	public void setPollingDate(Date pollingDate) {
		this.pollingDate = pollingDate;
	}

	public Integer getPollingStationCount() {
		return this.pollingStationCount;
	}

	public void setPollingStationCount(Integer pollingStationCount) {
		this.pollingStationCount = pollingStationCount;
	}

	public Integer getPollingStationElectoralAvgCnt() {
		return this.pollingStationElectoralAvgCnt;
	}

	public void setPollingStationElectoralAvgCnt(Integer pollingStationElectoralAvgCnt) {
		this.pollingStationElectoralAvgCnt = pollingStationElectoralAvgCnt;
	}

	public Integer getRepollBoothCount() {
		return this.repollBoothCount;
	}

	public void setRepollBoothCount(Integer repollBoothCount) {
		this.repollBoothCount = repollBoothCount;
	}

	public Date getRepollDate() {
		return this.repollDate;
	}

	public void setRepollDate(Date repollDate) {
		this.repollDate = repollDate;
	}

	public Date getResultDate() {
		return this.resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public String getResultRunnerCandidate() {
		return this.resultRunnerCandidate;
	}

	public void setResultRunnerCandidate(String resultRunnerCandidate) {
		this.resultRunnerCandidate = resultRunnerCandidate;
	}

	public String getResultRunnerParty() {
		return this.resultRunnerParty;
	}

	public void setResultRunnerParty(String resultRunnerParty) {
		this.resultRunnerParty = resultRunnerParty;
	}

	public Integer getResultRunnerVote() {
		return this.resultRunnerVote;
	}

	public void setResultRunnerVote(Integer resultRunnerVote) {
		this.resultRunnerVote = resultRunnerVote;
	}

	public String getResultWinnerCandidate() {
		return this.resultWinnerCandidate;
	}

	public void setResultWinnerCandidate(String resultWinnerCandidate) {
		this.resultWinnerCandidate = resultWinnerCandidate;
	}

	public String getResultWinnerParty() {
		return this.resultWinnerParty;
	}

	public void setResultWinnerParty(String resultWinnerParty) {
		this.resultWinnerParty = resultWinnerParty;
	}

	public Integer getResultWinnerVote() {
		return this.resultWinnerVote;
	}

	public void setResultWinnerVote(Integer resultWinnerVote) {
		this.resultWinnerVote = resultWinnerVote;
	}

	public String getStateCd() {
		return this.stateCd;
	}

	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getVotersGeneralMen() {
		return this.votersGeneralMen;
	}

	public void setVotersGeneralMen(Integer votersGeneralMen) {
		this.votersGeneralMen = votersGeneralMen;
	}

	public Integer getVotersGeneralOthers() {
		return this.votersGeneralOthers;
	}

	public void setVotersGeneralOthers(Integer votersGeneralOthers) {
		this.votersGeneralOthers = votersGeneralOthers;
	}

	public Integer getVotersGeneralTotal() {
		return this.votersGeneralTotal;
	}

	public void setVotersGeneralTotal(Integer votersGeneralTotal) {
		this.votersGeneralTotal = votersGeneralTotal;
	}

	public Integer getVotersGeneralWomen() {
		return this.votersGeneralWomen;
	}

	public void setVotersGeneralWomen(Integer votersGeneralWomen) {
		this.votersGeneralWomen = votersGeneralWomen;
	}

	public Integer getVotersMen() {
		return this.votersMen;
	}

	public void setVotersMen(Integer votersMen) {
		this.votersMen = votersMen;
	}

	public Integer getVotersOthers() {
		return this.votersOthers;
	}

	public void setVotersOthers(Integer votersOthers) {
		this.votersOthers = votersOthers;
	}

	public Integer getVotersOverseasMen() {
		return this.votersOverseasMen;
	}

	public void setVotersOverseasMen(Integer votersOverseasMen) {
		this.votersOverseasMen = votersOverseasMen;
	}

	public Integer getVotersOverseasOthers() {
		return this.votersOverseasOthers;
	}

	public void setVotersOverseasOthers(Integer votersOverseasOthers) {
		this.votersOverseasOthers = votersOverseasOthers;
	}

	public Integer getVotersOverseasTotal() {
		return this.votersOverseasTotal;
	}

	public void setVotersOverseasTotal(Integer votersOverseasTotal) {
		this.votersOverseasTotal = votersOverseasTotal;
	}

	public Integer getVotersOverseasWomen() {
		return this.votersOverseasWomen;
	}

	public void setVotersOverseasWomen(Integer votersOverseasWomen) {
		this.votersOverseasWomen = votersOverseasWomen;
	}

	public Integer getVotersPostalMen() {
		return this.votersPostalMen;
	}

	public void setVotersPostalMen(Integer votersPostalMen) {
		this.votersPostalMen = votersPostalMen;
	}

	public Integer getVotersPostalOthers() {
		return this.votersPostalOthers;
	}

	public void setVotersPostalOthers(Integer votersPostalOthers) {
		this.votersPostalOthers = votersPostalOthers;
	}

	public Integer getVotersPostalTotal() {
		return this.votersPostalTotal;
	}

	public void setVotersPostalTotal(Integer votersPostalTotal) {
		this.votersPostalTotal = votersPostalTotal;
	}

	public Integer getVotersPostalWomen() {
		return this.votersPostalWomen;
	}

	public void setVotersPostalWomen(Integer votersPostalWomen) {
		this.votersPostalWomen = votersPostalWomen;
	}

	public Integer getVotersProxyMen() {
		return this.votersProxyMen;
	}

	public void setVotersProxyMen(Integer votersProxyMen) {
		this.votersProxyMen = votersProxyMen;
	}

	public Integer getVotersProxyOthers() {
		return this.votersProxyOthers;
	}

	public void setVotersProxyOthers(Integer votersProxyOthers) {
		this.votersProxyOthers = votersProxyOthers;
	}

	public Integer getVotersProxyTotal() {
		return this.votersProxyTotal;
	}

	public void setVotersProxyTotal(Integer votersProxyTotal) {
		this.votersProxyTotal = votersProxyTotal;
	}

	public Integer getVotersProxyWomen() {
		return this.votersProxyWomen;
	}

	public void setVotersProxyWomen(Integer votersProxyWomen) {
		this.votersProxyWomen = votersProxyWomen;
	}

	public Integer getVotersRejectedOtherReasons() {
		return this.votersRejectedOtherReasons;
	}

	public void setVotersRejectedOtherReasons(Integer votersRejectedOtherReasons) {
		this.votersRejectedOtherReasons = votersRejectedOtherReasons;
	}

	public Integer getVotersTotal() {
		return this.votersTotal;
	}

	public void setVotersTotal(Integer votersTotal) {
		this.votersTotal = votersTotal;
	}

	public Integer getVotersWomen() {
		return this.votersWomen;
	}

	public void setVotersWomen(Integer votersWomen) {
		this.votersWomen = votersWomen;
	}

	public Integer getWithdrawnMen() {
		return this.withdrawnMen;
	}

	public void setWithdrawnMen(Integer withdrawnMen) {
		this.withdrawnMen = withdrawnMen;
	}

	public Integer getWithdrawnOther() {
		return this.withdrawnOther;
	}

	public void setWithdrawnOther(Integer withdrawnOther) {
		this.withdrawnOther = withdrawnOther;
	}

	public Integer getWithdrawnTotal() {
		return this.withdrawnTotal;
	}

	public void setWithdrawnTotal(Integer withdrawnTotal) {
		this.withdrawnTotal = withdrawnTotal;
	}

	public Integer getWithdrawnWomen() {
		return this.withdrawnWomen;
	}

	public void setWithdrawnWomen(Integer withdrawnWomen) {
		this.withdrawnWomen = withdrawnWomen;
	}

}