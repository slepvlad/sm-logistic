# ShipCalc — Shipping Cost Calculator

A Spring Boot REST API + UI that calculates container shipping costs.

## Formula
```
Base Cost    = quantity_sqm × price_per_sqm
Total Cost   = base_cost × currency_cross_rate
```

## API

### POST /api/shipping/calculate
```json
{
  "quantitySquareMeters": 33.5,
  "currencyCrossRate": 1.08,
  "pricePerSquareMeter": 45.00
}
```

**Response:**
```json
{
  "quantitySquareMeters": 33.5,
  "currencyCrossRate": 1.08,
  "pricePerSquareMeter": 45.0,
  "baseCost": 1507.5,
  "totalCostConverted": 1628.1,
  "currency": "USD"
}
```

### GET /api/shipping/health
Returns service status.

---

## Run with Docker (recommended)

```bash
# Build & start
docker-compose up --build

# App available at:
http://localhost:8080
```

## Run locally (requires Java 17 + Maven)

```bash
mvn spring-boot:run
```

---

## Deploy Online

### Option A — Railway
1. Push this repo to GitHub
2. Go to [railway.app](https://railway.app) → New Project → Deploy from GitHub
3. Railway auto-detects the Dockerfile ✅

### Option B — Render
1. Push to GitHub
2. New Web Service → Connect repo
3. Set **Dockerfile** as build method, port **8080**

### Option C — Any VPS (DigitalOcean, Hetzner, etc.)
```bash
# On your server:
git clone <your-repo>
cd shipping-calc
docker-compose up -d
```

---

## Roadmap
- [ ] Move shipping rate constants to database (PostgreSQL)
- [ ] Add multiple rate tiers (weight, distance, cargo type)
- [ ] Add authentication
- [ ] Swagger / OpenAPI docs
