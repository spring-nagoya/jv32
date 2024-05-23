package main

import (
	"encoding/json"
	"net"
	"net/http"
)

type Response struct {
	LocalIP  []string `json:"ip"`
	RemoteIP string   `json:"remote_ip"`
}

func main() {
	mux := http.NewServeMux()
	mux.HandleFunc("GET /", func(w http.ResponseWriter, r *http.Request) {
		// localIP を取得
		localIP, err := net.InterfaceAddrs()
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		var response Response
		for _, addr := range localIP {
			if ipnet, ok := addr.(*net.IPNet); ok && !ipnet.IP.IsLoopback() {
				if ipnet.IP.To4() != nil {
					response.LocalIP = append(response.LocalIP, ipnet.IP.String())
				}
			}
		}

		// リモートIPを取得
		remoteIP := r.RemoteAddr
		response.RemoteIP = remoteIP

		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(response)
	})

	http.ListenAndServe(":8080", mux)
}
