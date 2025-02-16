import React, { useEffect, useState } from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./index.css";
import reportWebVitals from "./reportWebVitals";
import Register from "./Component/Register";
import Login from "./Component/Login";
import Main from "./Component/Main"; // 메인 화면 컴포넌트
import RecordBody from "./Component/RecordBody";
import RankPage from "./Component/RankPage";

const App = () => {
  const [isKakaoLoaded, setIsKakaoLoaded] = useState(false);
  const [isPostcodeLoaded, setIsPostcodeLoaded] = useState(false);

  useEffect(() => {
    const kakaoApiKey = process.env.REACT_APP_KAKAO_API_KEY;
    
    if (!kakaoApiKey) {
      console.error("🚨 Kakao API 키가 설정되지 않았습니다! .env 파일을 확인하세요.");
      return;
    }

    const loadKakaoApi = () => {
      if (window.kakao && window.kakao.maps) {
        console.log("✅ Kakao Map API 이미 로드됨!");
        setIsKakaoLoaded(true);
        return;
      }

      console.log("🚀 Kakao Map API 로드 중...");
      const script = document.createElement("script");
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoApiKey}&libraries=services`;
      script.async = true;
      script.onload = () => {
        console.log("✅ Kakao Map API 로드 완료!");
        setIsKakaoLoaded(true);
      };
      script.onerror = () => console.error("🚨 Kakao Map API 로드 실패!");
      document.head.appendChild(script);
    };

    const loadPostcodeApi = () => {
      if (window.daum && window.daum.Postcode) {
        console.log("✅ 우편번호 API 이미 로드됨!");
        setIsPostcodeLoaded(true);
        return;
      }

      console.log("🚀 우편번호 API 로드 중...");
      const script = document.createElement("script");
      script.src = "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
      script.async = true;
      script.onload = () => {
        console.log("✅ 우편번호 API 로드 완료!");
        setIsPostcodeLoaded(true);
      };
      script.onerror = () => console.error("🚨 우편번호 API 로드 실패!");
      document.head.appendChild(script);
    };

    loadKakaoApi();
    loadPostcodeApi();

    return () => {
      console.log("🗑️ Kakao Map & 우편번호 API 제거");
      document.head.querySelectorAll("script[src*='kakao'], script[src*='postcode']").forEach((script) => script.remove());
    };
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/main" element={<Main />} />
        <Route path="/" element={<Login />} />
        <Route path="/recordbody" element={<RecordBody />} />
        <Route path="/rank" element={<RankPage />} />
      </Routes>
    </Router>
  );
};

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

reportWebVitals();
