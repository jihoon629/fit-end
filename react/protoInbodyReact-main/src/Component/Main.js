import React, { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import config from "../config";

export default function Main() {
  const navigate = useNavigate();
  const [bodyrecod, setBodyRecod] = useState([]);
  const [loading, setLoading] = useState(true);
  const useridRef = useRef(sessionStorage.getItem("userid"));

  const navigateToRecordBody = () => navigate("/recordbody");
  const navigateToRank = () => navigate("/rank");
  const navigateToTodo = () => navigate("/todo");

  // 로그아웃 처리
  const handleLogout = async () => {
    await fetch(`http://${config.SERVER_URL}/request/logout`, {
      method: "POST",
      credentials: "include",
    });

    sessionStorage.removeItem("userid");
    navigate("/login");
  };

  // 로그인 상태 확인 후 `userid` 가져오기
  useEffect(() => {
    fetch(`http://${config.SERVER_URL}/request/validate`, {
      method: "GET",
      credentials: "include", // 쿠키 자동 포함
    })
      .then((response) => {
        if (!response.ok) throw new Error("Unauthorized");
        return response.json();
      })
      .then((data) => {
        console.log("로그인 상태 확인 성공:", data);
        useridRef.current = data.userid;
        sessionStorage.setItem("userid", data.userid);

        // 사용자 신체 기록 가져오기
        return fetch(`http://${config.SERVER_URL}/download/recentuserbody/${data.userid}`, {
          method: "GET",
          credentials: "include",
          headers: { "Content-Type": "application/json" },
        });
      })
      .then((response) => response.json())
      .then((bodyData) => {
        console.log("신체 기록 응답 데이터:", bodyData);
        setBodyRecod(bodyData);
        setLoading(false);
      })
      .catch(() => {
        console.warn("인증 실패. 로그인 페이지로 이동");
        sessionStorage.removeItem("userid");
        navigate("/login");
      });
  }, [navigate]);

  if (loading) {
    return <p>📡 데이터를 불러오는 중입니다...</p>; // 로딩 중 메시지 유지
  }

  if (bodyrecod.length === 0 || bodyrecod[0] == null) {
    return (
      <div>
        <p>⚠️ 신체 기록이 없습니다. 데이터를 입력해주세요.</p>
        <button onClick={navigateToRecordBody}>기록 추가하기</button>
        <button onClick={handleLogout} style={{ marginLeft: "10px" }}>
          나가기
        </button>
      </div>
    );
  }

  return (
    <div>
      {useridRef.current ? (
        <>
          <h2>Main Screen</h2>
          <p>Welcome to the main screen!</p>
          <p>Logged in as: {useridRef.current}</p>

          <div>
            <h2>📊 InBody 결과</h2>
            <p><strong>📏 키:</strong> {bodyrecod[0].height} cm</p>
            <p><strong>⚖️ 몸무게:</strong> {bodyrecod[0].weight} kg</p>
            <p><strong>📉 체지방률:</strong> {bodyrecod[0].fatpercentage} %</p>
            <p><strong>💪 BMI:</strong> {bodyrecod[0].bmi}</p>
            <p><strong>🔥 InBody Score:</strong> {bodyrecod[0].inbodyScore}</p>
          </div>

          <button onClick={navigateToRank} style={{ marginLeft: "10px" }}>점수 랭킹 보기</button>
          <button onClick={navigateToRecordBody}>신체 정보 입력</button>
          <button onClick={navigateToTodo}>음식 다이어리</button>
          <button onClick={handleLogout} style={{ marginLeft: "10px" }}>로그아웃</button>
        </>
      ) : (
        <p>잘못된 접근</p>
      )}
    </div>
  );
}
