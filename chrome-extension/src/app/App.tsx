import './App.css'

const chips = {
  roles: ['Software Engineer', 'Full Stack Developer'],
  tech: ['React', 'TypeScript', 'Node.js'],
  industries: ['Fintech', 'SaaS'],
  locations: ['San Francisco, CA', 'Remote'],
  companies: ['Startup', 'Mid-size', 'Enterprise', 'FAANG'],
}

const weights = [
  ['Role Match', '90%'],
  ['Tech Stack', '75%'],
  ['Salary Range', '60%'],
  ['Company Fit', '80%'],
]

function ChipList({ items }: { items: string[] }) {
  return (
    <div className="chip-list">
      {items.map((item) => (
        <span className="chip" key={item}>
          {item} <span aria-hidden="true">x</span>
        </span>
      ))}
    </div>
  )
}

function App() {
  return (
    <main className="job-filter">
      <header className="topbar">
        <div className="brand">
          <span className="brand-icon" aria-hidden="true">
            <svg viewBox="0 0 24 24">
              <path d="M8 7V5.8C8 4.8 8.8 4 9.8 4h4.4c1 0 1.8.8 1.8 1.8V7" />
              <rect x="5" y="7" width="14" height="12" rx="2" />
              <path d="M9 7v12M15 7v12" />
            </svg>
          </span>
          <strong>JobFilter</strong>
          <span>for LinkedIn</span>
        </div>
        <button className="icon-button" type="button" aria-label="Settings">
          <svg viewBox="0 0 24 24">
            <path d="M12 15.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Z" />
            <path d="M19.4 15a1.7 1.7 0 0 0 .3 1.9l.1.1a2 2 0 0 1-2.8 2.8l-.1-.1a1.7 1.7 0 0 0-1.9-.3 1.7 1.7 0 0 0-1 1.5V21a2 2 0 0 1-4 0v-.1a1.7 1.7 0 0 0-1-1.5 1.7 1.7 0 0 0-1.9.3l-.1.1A2 2 0 0 1 4.2 17l.1-.1a1.7 1.7 0 0 0 .3-1.9 1.7 1.7 0 0 0-1.5-1H3a2 2 0 0 1 0-4h.1a1.7 1.7 0 0 0 1.5-1 1.7 1.7 0 0 0-.3-1.9L4.2 7A2 2 0 0 1 7 4.2l.1.1a1.7 1.7 0 0 0 1.9.3 1.7 1.7 0 0 0 1-1.5V3a2 2 0 0 1 4 0v.1a1.7 1.7 0 0 0 1 1.5 1.7 1.7 0 0 0 1.9-.3l.1-.1A2 2 0 0 1 19.8 7l-.1.1a1.7 1.7 0 0 0-.3 1.9 1.7 1.7 0 0 0 1.5 1h.1a2 2 0 0 1 0 4h-.1a1.7 1.7 0 0 0-1.5 1Z" />
          </svg>
        </button>
      </header>

      <section className="profile-picker" aria-labelledby="search-profile">
        <label id="search-profile">Search Profile</label>
        <div className="picker-row">
          <button className="select-button" type="button">
            Senior SWE Search
            <span aria-hidden="true">⌄</span>
          </button>
          <button className="new-button" type="button">
            + New
          </button>
        </div>
      </section>

      <nav className="tabs" aria-label="Profile sections">
        <button className="active" type="button">Configuration</button>
        <button type="button">Results</button>
      </nav>

      <form className="settings-form">
        <label className="field">
          <span>Profile Name</span>
          <input value="Senior SWE Search" readOnly />
        </label>

        <label className="field">
          <span>Industries</span>
          <ChipList items={chips.industries} />
        </label>

        <label className="field">
          <span>Preferred Locations</span>
          <ChipList items={chips.locations} />
        </label>

        <fieldset className="work-type">
          <legend>Work Type</legend>
          <button className="selected" type="button">Remote</button>
          <button className="selected" type="button">Hybrid</button>
          <button type="button">Onsite</button>
        </fieldset>

        <div className="two-up">
          <label className="field">
            <span>Min Salary (USD)</span>
            <input value="$ 150000" readOnly />
          </label>
          <label className="field">
            <span>Seniority</span>
            <select defaultValue="Senior">
              <option>Senior</option>
            </select>
          </label>
        </div>

        <fieldset className="company-size">
          <legend>Company Size</legend>
          {chips.companies.map((item, index) => (
            <button className={index < 2 ? 'selected' : ''} key={item} type="button">
              {item}
            </button>
          ))}
        </fieldset>

        <label className="field">
          <span>AI Scoring Instructions</span>
          <textarea
            value="Prioritize companies with strong engineering culture, good work-life balance, and equity. Avoid companies with frequent layoffs or poor Glassdoor ratings."
            readOnly
          />
        </label>

        <section className="weights" aria-labelledby="importance-weights">
          <h2 id="importance-weights">Importance Weights</h2>
          {weights.map(([label, value]) => (
            <label className="range-field" key={label}>
              <span>
                {label}
                <strong>{value}</strong>
              </span>
              <input type="range" min="0" max="100" value={value.replace('%', '')} readOnly />
            </label>
          ))}
        </section>

        <button className="save-button" type="button">Save Profile</button>
      </form>

      <footer className="scan-footer">
        <p><span aria-hidden="true"></span>247 scanned · 12 recommended</p>
        <time>just now</time>
        <button type="button">
          <span aria-hidden="true">⌗</span>
          Scan LinkedIn Jobs
        </button>
      </footer>
    </main>
  )
}

export default App
