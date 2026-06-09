CREATE TABLE users (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,

                       email TEXT NOT NULL UNIQUE,
                       display_name TEXT,

                       created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE jobs (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,

                      source TEXT NOT NULL,
                      external_job_id TEXT,
                      fingerprint TEXT NOT NULL UNIQUE,
                      url TEXT,

                      title TEXT NOT NULL,
                      company TEXT NOT NULL,
                      location TEXT,
                      posted_date TEXT,

                      description TEXT,

                      metadata_level TEXT NOT NULL DEFAULT 'BASIC',

                      first_seen_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      last_seen_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                      CONSTRAINT uq_jobs_source_external_job_id
                          UNIQUE (source, external_job_id)
);

CREATE TABLE search_profiles (
                                 id INTEGER PRIMARY KEY AUTOINCREMENT,

                                 user_id INTEGER NOT NULL,

                                 name TEXT NOT NULL,

                                 custom_instructions TEXT,
                                 min_score INTEGER,
                                 is_default INTEGER NOT NULL DEFAULT 0,

                                 created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                 CONSTRAINT fk_search_profiles_user
                                     FOREIGN KEY (user_id)
                                         REFERENCES users(id),

                                 CONSTRAINT uq_search_profile_name_per_user
                                     UNIQUE (user_id, name)
);

CREATE TABLE job_ratings (
                             id INTEGER PRIMARY KEY AUTOINCREMENT,

                             user_id INTEGER NOT NULL,
                             job_id INTEGER NOT NULL,
                             search_profile_id INTEGER NOT NULL,

                             rating_type TEXT NOT NULL,

                             overall_score INTEGER NOT NULL,
                             decision TEXT NOT NULL,

                             title_match_score INTEGER,
                             career_goal_score INTEGER,
                             company_culture_score INTEGER,
                             remote_work_score INTEGER,
                             stability_score INTEGER,

                             summary TEXT,

                             reasons_json TEXT,
                             concerns_json TEXT,

                             created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT fk_job_ratings_user
                                 FOREIGN KEY (user_id)
                                     REFERENCES users(id),

                             CONSTRAINT fk_job_ratings_job
                                 FOREIGN KEY (job_id)
                                     REFERENCES jobs(id),

                             CONSTRAINT fk_job_ratings_search_profile
                                 FOREIGN KEY (search_profile_id)
                                     REFERENCES search_profiles(id),

                             CONSTRAINT uq_job_rating
                                 UNIQUE (
                                         user_id,
                                         job_id,
                                         search_profile_id,
                                         rating_type
                                     )
);

CREATE TABLE sys_lov (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         lov_name TEXT NOT NULL,
                         lov_value TEXT NOT NULL,
                         display_value TEXT NOT NULL,
                         sort_order INTEGER DEFAULT 0,
                         is_active INTEGER DEFAULT 1,

                         UNIQUE (lov_name, lov_value)
);

CREATE TABLE search_profile_extended_fields (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        search_profile_id INTEGER NOT NULL,

                        field_name TEXT NOT NULL,
                        lov_name TEXT,
                        lov_value TEXT,
                        freeform_value TEXT,

                        weight INTEGER,
                        is_required INTEGER DEFAULT 0,

                        FOREIGN KEY (search_profile_id)
                            REFERENCES search_profiles(id)
                            ON DELETE CASCADE,

                        FOREIGN KEY (lov_name, lov_value)
                            REFERENCES sys_lov(lov_name, lov_value)
);

CREATE INDEX idx_jobs_company
    ON jobs(company);

CREATE INDEX idx_jobs_title
    ON jobs(title);

CREATE INDEX idx_search_profiles_user_id
    ON search_profiles(user_id);

CREATE INDEX idx_search_profile_extended_fields_search_profile_id
    ON search_profile_extended_fields(search_profile_id);

CREATE INDEX idx_job_ratings_user_id
    ON job_ratings(user_id);

CREATE INDEX idx_job_ratings_job_id
    ON job_ratings(job_id);

CREATE INDEX idx_job_ratings_search_profile_id
    ON job_ratings(search_profile_id);